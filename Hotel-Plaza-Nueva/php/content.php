<?php
  /*  class Habitacion{
        var $nombrehab;
        var $nom;
        var $idhab;
        var $rutaimg;
        var $altext;
        var $descripcion;

        function Habitacion($nomh,$id,$nom,$ruta,$altt,$desc){
            $this->nombrehab=$nomh;
            $this->idhab=$id;
            $this->nom=$nom;
            $this->rutaimg=$ruta;
            $this->altext=$altt;
            $this->descripcion=$desc;
        }
    }

    class ContenedorHabitaciones{
        var $habitaciones = array();

        function add($hab){
            array_push($this->habitaciones,$hab);
        }

        function printall(){
            for($i=0 ;$i < count($this->habitaciones);$i++){
                echo
                '<h3 id='.$this->habitaciones[$i]->nombrehab.'>'
                .$this->habitaciones[$i]->nom.
                '</h3>
                <div id='.$this->habitaciones[$i]->idhab.' class="contentarea">
                <img src='.$this->habitaciones[$i]->rutaimg. ' class="left" alt='.$this->habitaciones[$i]->altext.' width=270px height=170px/>'.$this->habitaciones[$i]->descripcion.'</div><form action="consultar_reservas.php"> Seleccione fecha de entrada y fecha de salida: <input type="date" name="fentrada"> <input type="date" name="fsalida"><button type="submit" value="Submit">Buscar</button></form><hr>';

            }
        }
    }

    $contenedor = new ContenedorHabitaciones();

    $habdoble = new Habitacion("habtwin","twin","Habitación Doble o Twin (2 camas)","../imagenes/DobleTwin.png","Habitación Doble Twin","<p>En nuestras habitaciones standard disfrutará de todo el equipamiento y comodidades que su estancia en Granada merece.</p>
        <p>Servicios ofertados: Teléfono, Amenities en el baño, Conexión Wi-Fi a Internet, Aire acondicionado, TV vía satélite, Habitaciones conectadas, Adaptadas a personas con movilidad reducida, Cunas (bajo petición), Secador de pelo, Caja de seguridad.</p>
        <p>Precios: 49.00€ (Alojamiento) - 63.00€ (Alojamiento y desayuno)</p>
        <p>Condiciones especiales: Cancelación sin penalización hasta 24h antes de la entrada del cliente, después de ese período o caso de no-show el hotel podrá facturar la primera noche de la reserva.</p>");

    $habsuperior = new Habitacion("habsuperior","superior","Habitación Doble Superior","../imagenes/DobleSuperior.png","Habitación Doble Superior","  <p>Disfrute de una magnífica vista de Plaza Nueva y el centro de Granada desde nuestras habitaciones superiores.</p>
        <p>Servicios ofertados: Teléfono, Amenities en el baño, Conexión Wi-Fi a Internet, Aire acondicionado, TV vía satélite, Habitaciones conectadas, Adaptadas a personas con movilidad reducida, Cunas (bajo petición), Secador de pelo, Caja de seguridad.</p>
        <p>Precios: 71.00€ (Alojamiento) - 85.00€ (Alojamiento y desayuno)</p>
        <p>Condiciones especiales: Cancelación sin penalización hasta 24h antes de la entrada del cliente, después de ese período o caso de no-show el hotel podrá facturar la primera noche de la reserva.</p>");

    $habtriple = new Habitacion("habtriple","triple","Habitación Triple","../imagenes/Triple.png","Habitación Triple","  <p>En nuestras habitaciones triples podrá disfrutar de sus vacaciones en familia o con amigos en el centro de Granada.</p>
        <p>Servicios ofertados: Teléfono, Amenities en el baño, Conexión Wi-Fi a Internet, Aire acondicionado, TV vía satélite, Habitaciones conectadas, Adaptadas a personas con movilidad reducida, Cunas (bajo petición), Secador de pelo, Caja de seguridad.</p>
        <p>Precios: 90.00€ (Alojamiento) - 105.00€ (Alojamiento y desayuno)</p>
        <p>Condiciones especiales: Cancelación sin penalización hasta 24h antes de la entrada del cliente, después de ese período o caso de no-show el hotel podrá facturar la primera noche de la reserva.</p>");

    $contenedor->add($habdoble);
    $contenedor->add($habsuperior);
    $contenedor->add($habtriple);


    class ContenedorPromociones{
        var $promociones = array();

        function add($prom){
            array_push($this->promociones,$prom);
        }

        function printall(){
            for($i=0 ;$i < count($this->promociones);$i++){
                echo
                '<h3 id='.$this->promociones[$i]->etiqueta.'>'
                .$this->promociones[$i]->descr.
                '</h3>
                <div id='.$this->promociones[$i]->id.' class="contentarea">
                <img src='.$this->promociones[$i]->rutaimg. ' class="left" alt='.$this->promociones[$i]->altext.' width=270px height=170px/>'.$this->promociones[$i]->descripcion.'</div><hr>';
            }
        }
    }

$contenedorprom = new ContenedorPromociones();
    class Promocion{
        var $etiqueta;
        var $descr;
        var $id;
        var $rutaimg;
        var $altext;
        var $descripcion;

        function Promocion($etiqueta,$descr,$id,$rutaimg,$altext,$descripcion){
            $this->etiqueta=$etiqueta;
            $this->descr=$descr;
            $this->id=$id;
            $this->rutaimg=$rutaimg;
            $this->altext=$altext;
            $this->descripcion=$descripcion;
        }
    }


    $alhambra = new Promocion("visalhambra","Visita a la Alhambra","alhambra","../imagenes/Alhambra.png","VisitaAlhambra","  <p>Incluye:  espectáculo flamenco y visita guiada a la Alhambra.</p>
        <p>Descubrirá con nosotros la única Ciudad Medieval Musulmana mejor conservada del mundo, la Alhambra; visitando sus palacios, Mexuar, Comares, Leones, Generalife; paseando por sus patios, de los Arrayanes, la Reja, la Acequia, la Sultana; y disfrutando de sus jardines, de Partal, de la Medina y por suspuesto del Generalife con sus graciosos juegos de agua, y su laberíntico diseño.</p>
        <p>Incluye además: Recogida, ida y vuelta, en bus en el hotel, azafata acompañante, entradas al monumento y guía oficial.</p>
        <p>Duración: Aproximadamente 3 horas.</p>
        <p>Horario: Según la disponibilidad a la hora de la reserva.</p>
        <p>Release de 48 horas.</p>
        <p>Política de cancelación de 72 horas.</p>");

    $sierra = new Promocion("vissierra","Visita a Sierra Nevada","sierra","../imagenes/SierraNevada.jpg","descuento","    <p>Incluye forfait para dos días en la Estación de Esquí.</p>
        <p>Sierra Nevada, Estación de Esquí y Montaña de Andalucía, se encuentra en el Sistema Penibético, en el entorno del Espacio Natural de Sierra Nevada. Con una ubicación privilegiada, en un nudo de conexiones con el resto de provincias andaluzas, dista 31 kilómetros de Granada capital y menos de 100 kilómetros de la Costa Tropical de Granada, y la Estación de Esquí tiene pistas de todos los tipos y más de 100 kilómetros esquiables.</p>
        <p>Incluye además: Recogida, ida y vuelta, en bus en el hotel, y clases de esquí de nivel Principiante-Medio.</p>
        <p>Duración de las clases: Aproximadamente 6 horas (3 por la mañana y 3 por la tarde).</p>
        <p>Horario: Disponible únicamente en fin de semana (Viernes a domingo). Las clases serán de 10:00h-13:00h y de 16:00h-19:00h.</p>

        <p>Release de 48 horas.</p>
        <p>Política de cancelación de 72 horas.</p>
        <p>La oferta se aplica a un MÁXIMO DE 3 PERSONAS.</p>");

    $granadacf = new Promocion("visgranadacf","Descuento en un partido del Granada C.F.","granadacf","../imagenes/GranadaCF.jpg","descuento","    <p>Incluye: Descuento del 20% en el precio de la entrada para un partido de fútbol del Granada C.F. contra el rival que desee.</p>
        <p>El Granada C.F. es una S.A.D. fundada hace más de 80 años y que actualmente milita en la Primera División Española, junto a equipos como el Real Madrid, el Atlético o el F.C. Barcelona. A lo largo de su historia ha conseguido la sexta posición en dos ocasiones y ha sido subcampeón de la Copa del Rey. Juega en el Estadio Nuevo los Cármenes, con capacidad para 23.000 espectadores, y tiene unos 11.500 abonados.</p>
        <p>Incluye además: Recogida, ida y vuelta, en bus en el hotel.</p>
        <p>Duración total: Aproximadamente 3 horas (Incluyendo los 90 minutos del partido).</p>
        <p>Horario: Depende de los horarios establecidos por la LFP para los partidos del Granada C.F.</p>
        <p>Precio: 15 € (bus) + Precio Preferencia Sector B con un 20% de descuento sobre el partido elegido.</p>
        <p>Release de 24 horas.</p>
        <p>Política de cancelación de 48 horas.</p>
        <p>La oferta se aplica a un MÁXIMO DE 4 PERSONAS.</p>");

    $contenedorprom->add($alhambra);
    $contenedorprom->add($sierra);
    $contenedorprom->add($granadacf);

    $fnombre="";
    $fmensaje="";
    $femail="";*/



    /*  Aqui comienza la practica 4: SQL */

    include "./factura.php";

    class ContenedorActividadesSQL{
      var $seleccion;
      var $resultado;
      var $numfilas;

      function ContenedorActividadesSQL(){
        $this->seleccion = "SELECT * FROM actividades  ";
        $this->resultado = mysql_query($this->seleccion);
        $this->numfilas = mysql_num_rows ($this->resultado);
      }

      function Printall(){

        for($i=0;$i<$this->numfilas;$i++)
        {
          $actividad = mysql_fetch_array ($this->resultado);

          echo
          '<h3 id='.$actividad["etiqueta"].'>'
          .$actividad["nombre"].
          '</h3>
          <div id='.$actividad["id"].' class="contentarea">
          <img src='.$actividad["urlimg"]. ' class="left" alt='.$actividad["altertext"].' width=270px height=170px/>'.$actividad["descripcion"].' El precio por realizar esta actividad es de: '. $actividad["precio"]. ' euros por persona <hr></div>';

        }
      }
    }


        class ContenedorPromocionesSQL{
          var $seleccion;
          var $resultado;
          var $numfilas;

          function ContenedorPromocionesSQL(){
            $this->seleccion = "SELECT * FROM promociones";
            $this->resultado = mysql_query($this->seleccion);
            $this->numfilas = mysql_num_rows ($this->resultado);
          }

          function Printall(){

            for($i=0;$i<$this->numfilas;$i++)
            {
              $promocion = mysql_fetch_array ($this->resultado);

              echo
              '<h3 id='.$promocion["etiqueta"].'>'
              .$promocion["nombre"].
              '</h3>
              <div id='.$promocion["id"].' class="contentarea">
                <img src='.$promocion["urlimg"]. ' class="left" alt='.$promocion["altertext"].' width=270px height=170px/>'
                .$promocion["descripcion"].' <hr> </div>
              <hr>';

            }
          }
        }



      $contenedoractisql = new ContenedorActividadesSQL();
    $contenedorpromosql = new ContenedorPromocionesSQL();

?>

<?php if($seccion == "inicio"){ ?>
    <div id="content">
        <div id="normalcontent">
            <h3>BIENVENIDO AL HOTEL PLAZA NUEVA</h3>
            <br>
            <h4>Descubra nuestras mejores ofertas y promociones:</h4>
        </div>

        <div id="primarycontainer">
            <div id="primarycontent">
                <hr>
				<img src="imagenes/hotel_miniatura.jpg" class="left" alt="Miniatura del hotel" width=200px height=220px/>
                <p><b>El Hotel Plaza Nueva está situado en el pleno centro monumental, comercial y administrativo de Granada, a 10 minutos de la Alhambra.</b></p>
                <p>El hotel ofrece una amplia y eficiente gama de servicios extra que satisfarán cualquier necesidad que le surja, reservas a shows de flamenco o visitas turísticas por la ciudad y la Alhambra.</p>
                <p>El hotel le ofrece 25 amplias y luminosas habitaciones repartidas sobre 3 plantas con ascensor.</p>
                <p>Cada planta del hotel y cada habitación poseen un nombre y encanto propio. Este nombre es una representación de un evento importante en la vida e historia de Granada. No tienen tarjetas magnéticas para abrir las puertas de las habitaciones, preferimos la originalidad que proporciona una tradicional llave.</p>
                <p>El hotel ofrece el servicio de desayuno continental en la cafetería, donde podrá disfrutar de conexión WIFI. Asimismo podrá obtener mediante pago del servicio conexión WIFI en su habitación.</p>
                <p>Confiamos en que disfrute plenamente su estancia entre nosotros así como de nuestra bella ciudad.</p>
                <p>Número de registro del hotel en la consejería de turismo de Andalucía: H/GR/01181.</p>
				<hr>
                <h3>Promociones</h3>
                <div class="contentarea">
                    <p>Descubre las mejores propuestas para sacar el máximo partido a tu estancia en Granada. El Hotel Plaza Nueva ofrece una amplia variedad de actividades lúdicas, haga de su estancia una estancia inolvidable.</p>
                    <a href="index.php?seccion=promociones" class="more">MÁS INFORMACIÓN</a>
                </div>
                <hr>

                <h3>Contacto</h3>
                <div class="contentarea">
                    <p>Conozca toda la información necesaria para poder localizarnos y llegar a nosotros sin problemas.</p>
                    <a href="index.php?seccion=contacto" class="more">MÁS INFORMACIÓN</a>
                </div>
                <hr>
            </div>
        </div>
    </div>
<?php } ?>

<?php if($seccion == "habitaciones"){ ?>
    <div id="content">
        <div id="normalcontent">
            <h3>Habitaciones del Hotel Plaza Nueva</h3>
        </div>

        <div id="primarycontainer">
            <div id="primarycontent">
				<hr>
				<p>Nuestras habitaciones con vistas a <b>Plaza Nueva</b> y a la <b>Torre de la Vela</b>, que pertenece al recinto de la Alhambra, le permitirán disfrutar de una perspectiva distinta de la ciudad. Disfrute de esta experiencia por un pequeño suplemento.</p>
                <p>Asimismo el hotel dispone de habitaciones interiores, recomendables para personas que desean descansar con la máxima tranquilidad.</p>
                <p>Las habitaciones también disponen de un cuarto de baño completo, aire acondicionado, TV, teléfono directo y una caja de seguridad gratuita para su mayor tranquilidad.</p>
				<hr>
                <?php $contenedor->printall(); ?>
            </div>
        </div>
    </div>
<?php } ?>

<?php if($seccion == "promociones"){ ?>
    <div id="content">
        <div id="normalcontent">
            <h3>Promociones y ofertas del Hotel Plaza Nueva</h3>
        </div>

        <div id="primarycontainer">
            <div id="primarycontent">
                <hr>
                <?php
                    $contenedoractisql->Printall();
                    $contenedorpromosql->Printall();
                    ?>
            </div>
        </div>
    </div>
<?php } ?>

<?php if($seccion == "contacto"){ ?>
    <div id="content">
        <div id="normalcontent">
            <h3>Cómo llegar al hotel Plaza Nueva</h3>
        </div>

        <div id="primarycontainer">
            <div id="primarycontent">
            <hr>
			<p>Para llegar al Hotel Plaza Nueva toma el Anillo de Granada, dirección Motril-Sierra Nevada, y coge la salida número 128: Centro-Méndez Núñez. Continúa por la avenida Fuente Nueva-Severo Ochoa y, sin tomar el túnel, gira a la derecha en la Avenida de la Constitución.</p>
            <p>Al final hay una rotonda con una bandera de España. Gira a la izquierda en la Avenida del Hospicio (deja los Jardines del Triunfo a tu izquierda) y gira a la derecha (hay un semáforo en la intersección), Avenida Capitán Moreno.</p>
            <p>Sigue recto hasta el final de la avenida y en la rotonda continúa recto. Luego gira a la izquierda (justo enfrente de un hotel) y pasa bajo el Arco o Puerta Elvira. Continúa por la calle Elvira. Hacia el final de la calle verás señales luminosas. Continúa recto y verás un kiosco. Al final de la calle Pan se encuentra la entrada principal del Hotel Plaza Nueva. Se puede llegar hasta la puerta del hotel para descargar el equipaje.</p>
            <p>Si has reservado aparcamiento, situado a sólo 100 metros del hotel, te acompañaremos. La tarifa de aparcamiento es de 20,00 € al día y la hora de salida es a las 12:00. Recuerda que si cancelas el estacionamiento en el día de llegada, debes pagar el 50% del importe de la tarifa, ya que las plazas son limitadas.</p>
			<hr>
            <h3>Información de Contacto</h3>
            <div class="contentarea">
                <iframe id="mapa" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1589.4879384119486!2d-3.597400708345436!3d37.17704425399749!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd71fcb8cb9390e1%3A0x9253186efccf153a!2sHotel+Plaza+Nueva!5e0!3m2!1ses!2ses!4v1457088786880" width="600" height="450" frameborder="0" allowfullscreen></iframe>
                <p>Dirección: Imprenta, nº 2</p>
                <p>Localidad: Granada</p>
                <p>Provincia: Granada</p>
                <p>País: España</p>
                <p>Teléfono: +34 958 21 52 73</p>
                <p>Fax: +43 958 22 57 65</p>
                <p>Email: contactohotelplazanueva@gmail.com</p>
                <p><a href="https://www.facebook.com/Hotel-Plaza-Nueva-176542882374100/?fref=ts" target="_blank">Síguenos en Facebook</a></p>
                <p><a href="https://twitter.com/HOTELPLAZANUEVA" target="_blank">Síguenos en Twitter</a></p>
            </div>

            <hr>
            <h5>CONTACTE CON NOSOTROS:</h5>
            <?php
                if( isset($_POST["femail"]) && isset($_POST["ftelefono"]) && isset($_POST["fmensaje"]) ){
                    $fnombre=$_POST["fnombre"];
                    $femail=$_POST["femail"];
                    $fmensaje=$_POST["fmensaje"];

                    require './lib/PHPMailerAutoload.php';
                    $mail = new PHPMailer;
                    $mail->isSMTP();                                      // Set mailer to use SMTP
                    $mail->Host = 'smtp.gmail.com';  // Specify main and backup SMTP server
                    $mail->SMTPAuth = true;                               // Enable SMTP authentication
                    $mail->Username = 'contactohotelplazanueva@gmail.com';                 // SMTP username
                    $mail->Password = 'hotelplazanueva123';                           // SMTP password
                    $mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
                    $mail->Port = 587;                                    // TCP port to connect to
                    $mail->From = $femail;
                    $mail->FromName = $fnombre;
                    $mail->addAddress('contactohotelplazanueva@gmail.com');     // Add a recipient
                    $mail->addAddress($femail);     // Add a recipient
                    $mail->Subject = 'Contacto';
                    $mail->Body    = $fmensaje;

                    if(!$mail->Send()){
                        echo '<script type="text/javascript">alert("Se ha producido un error a la hora de enviar el correo electrónico.");</script>';
                    }
                    else {
						echo '<script type="text/javascript">alert("¡¡Muchas gracias por contactar con nosotros!!\nRecibirá su respuesta en un plazo máximo de 24 horas.\nPuede además revisar el mensaje mandado en su correo electrónico.");</script>';
                    }
                }
            ?>

            <form action=# method="post"  >
                <fieldset>
                <legend>Indique los siguientes datos:</legend>
                Nombre: <input type="text" name="fnombre"  size="30" required>
                Apellidos: <input type="text" name="fapellidos" size="30" required><br><br>
                Teléfono: <input type="text" name="ftelefono" pattern="^([0|\+[0-9]{1,5})?([1-9][0-9]{9})$" size="20" required>
                Email: <input type="email" name="femail" size="20" required><br><br>
                Mensaje: <br><textarea type="text" name="fmensaje"   cols="130" rows="10" required></textarea><br><br>
                <input type="submit" value="Enviar">
                </fieldset>
            </form>

            </div>
        </div>
    </div>
<?php } ?>
