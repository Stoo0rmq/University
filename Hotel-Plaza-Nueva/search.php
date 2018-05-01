<?php
$con = mysql_connect("localhost", "root", "") or exit("No se pudo conectar con el servidor") ;
$abreBD = mysql_select_db("hotel", $con) ;
mysql_query("SET NAMES 'utf8'") ;

if (!$abreBD) {
  die('No se pudo abrir la base de datos.Error:' . mysql_error()) ;
}
    $key=$_GET['q'];
    $array = array();
  //  $query=mysql_query("SELECT * FROM reservas  where correousuario LIKE '$key%'");
    $query=mysql_query("SELECT * FROM reservas  where correousuario LIKE '$key%'");
    $numero_filas = mysql_num_rows($query);
    for($i=0;$i<$numero_filas;$i++)
    {
        $row = mysql_fetch_assoc($query);
      $array[] = $row['localizador'];
    }
    echo json_encode($array);
?>
