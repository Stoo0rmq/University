<!--
Authors: Balti and Borja
Bill creation using FPDF

-->


<?php
require('./fpdf/fpdf.php') ;

    class Factura{
        var $preciofinal ;
        var $nombre;
        var $apellidos;
        var $correo;
        var $idreserva;
    function Factura(){
        $this->preciofinal = 0 ;
    }

    function incrementarPrecioFactura($precio){
        $this->preciofinal += $precio ;
    }

    function aplicarDescuentoFactura($dto){
        $this->preciofinal =  $this->preciofinal - ($this->preciofinal * ($dto/100)) ;
    }

    function generarPDF(){



        $pdf=new FPDF() ;
        $pdf->AddPage() ;
        $pdf->SetFont('Arial', 'B', 20);
    $pdf->SetFillColor(36, 96, 84);
    $pdf->SetTextColor(225);
    $pdf->Cell(0, 30, "Hotel Plaza Nueva", 0, 1, 'C', true);
          $pdf->SetTextColor(0);
        $pdf->SetFont('Arial', '', 12);
        $pdf->SetY(50);
        $pdf->Cell(100, 13, utf8_decode("Estimado $this->nombre $this->apellidos: "));
        $pdf->SetY(55);
        $pdf->Cell(100, 13, utf8_decode("con relación a su reserva número $this->idreserva le informamos que el precio final que tendrá "));
        $pdf->SetY(60);
        $pdf->Cell(100, 13, utf8_decode("que abonar el día de entrada, será de  $this->preciofinal euros (IVA incluido)"));
        $pdf->SetFont('Arial', '');

        $pdf->SetXY(0,0);
        $pdf->Cell(0, 170, "Muchas gracias por su reserva.", 'T', 0, 'C');


        $resultadohotel = mysql_query("SELECT * FROM hotel") ;
        $hotelconcreto = mysql_fetch_assoc($resultadohotel) ;

        $direccion=$hotelconcreto['direccion'];
        $localidad=$hotelconcreto['localidad'];
        $provincia=$hotelconcreto['provincia'];
        $pais=$hotelconcreto['pais'];
        $nombre=$hotelconcreto['nombre'];
        $email=$hotelconcreto['email'];
        $telefono=$hotelconcreto['telefono'];
        $fax=$hotelconcreto['fax'];

        $pdf->SetTextColor(0);
        $pdf->SetXY(0,-60);
        $pdf->Cell(0, 20, utf8_decode("$email $telefono $fax  "), 'T', 0, 'C');
        $pdf->SetXY(0,-65);
        $pdf->Cell(0, 20, utf8_decode(" $nombre $direccion $localidad $provincia $pais "), '' ,0, 'C');

        $pdf->Output(''.$this->idreserva.'.pdf','F') ;
    }

    function enviarfactura($idreserva){
      echo 'EL ID VALE XD '.$idreserva. '';
        $datosres = mysql_query("SELECT * FROM reservas WHERE localizador = '$idreserva'");
        $datosconcretos = mysql_fetch_assoc(  $datosres);
        $correouser = $datosconcretos["correousuario"];
        $datosus = mysql_query("SELECT * FROM usuarios WHERE correo = '$correouser'");
        $datosusconcreto = mysql_fetch_assoc(  $datosus);

        $this->nombre = $datosusconcreto["nombre"];
        $this->apellidos = $datosusconcreto["apellidos"];
        $this->correo = $datosusconcreto["correo"];
        $this->idreserva= $idreserva;
        $this->generarPDF() ;

        require './lib/PHPMailerAutoload.php' ;
        $mail = new PHPMailer ;
        $mail->isSMTP() ;
        $mail->Host = 'smtp.gmail.com' ;
        $mail->SMTPAuth = true ;
        $mail->Username = 'contactohotelplazanueva@gmail.com' ;
        $mail->Password = 'hotelplazanueva123' ;
        $mail->FromName = "Hotel Plaza Nueva";

        $mail->SMTPSecure = 'tls' ;
        $mail->Port = 587 ;
        $mail->addAddress('contactohotelplazanueva@gmail.com') ;
        $mail->addAddress($this->correo) ;
        $mail->Subject = 'Código de reserva: '.$this->idreserva. '' ;
        $mail->CharSet = 'UTF-8' ;
        $mail->AddAttachment(''.$this->idreserva.'.pdf') ;

        $resultadohotel = mysql_query("SELECT * FROM hotel") ;
        $hotelconcreto = mysql_fetch_assoc($resultadohotel) ;
        $this->infohotel=$hotelconcreto;
        $mail->Body = '
        <h1><a id="Hotel_Plaza_Nueva_0"></a>Hotel Plaza Nueva</h1>
        <p>Estimado '.$this->nombre.' '.$this->apellidos.' .
        Nos complace informarle de que su reserva en el hotel ha sido efectuada. El número de la reserva es '.$this->idreserva.' y la cantidad que deberá abonar en el hotel será de '.$this->preciofinal.' euros.</p>
        <p>La factura total viene adjunta en este correo. Si tiene alguna duda, puede contactar con nosotros mediante teléfono o email.</p>
        <p>'.$hotelconcreto["nombre"].'</p>
        <p>'.$hotelconcreto["direccion"].'</p>
        <p>'.$hotelconcreto["telefono"].'</p>
        <p>'.$hotelconcreto["localidad"].', '.$hotelconcreto["provincia"].', '.$hotelconcreto["pais"].'</p>

      '

 ;
        $mail->IsHTML(true);
        if(!$mail->Send()){
            echo '<script type="text/javascript">alert("Se ha producido un error a la hora de enviar el correo electrónico.");</script>' ;
        }

        else{
            echo '<script type="text/javascript">alert("¡¡La factura ha sido enviada a su correo de usuario!!");</script>' ;
        }
    }
}

?>
