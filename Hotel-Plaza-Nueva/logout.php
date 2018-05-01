<!--
Authors: Balti and Borja
Logout function
-->


<?php
    session_start() ;
    session_destroy() ;

    header('location: index.php') ;
?>
