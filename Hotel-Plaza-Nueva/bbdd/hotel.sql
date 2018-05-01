-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-05-2016 a las 19:05:33
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hotel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividades`
--

CREATE TABLE `actividades` (
  `nombreactividad` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci NOT NULL,
  `altertext` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `precio` int(32) NOT NULL,
  `urlimg` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `actividades`
--

INSERT INTO `actividades` (`nombreactividad`, `descripcion`, `altertext`, `precio`, `urlimg`) VALUES
('Visita a la Alhambra', '<p>Incluye: 2 noches de alojamiento en habitaciones estándar, desayuno continental, espectáculo flamenco y visita guiada a la Alhambra.</p>\r\n<p>Descubrirá con nosotros la única Ciudad Medieval Musulmana mejor conservada del mundo, la Alhambra; visitando sus palacios, Mexuar, Comares, Leones, Generalife; paseando por sus patios, de los Arrayanes, la Reja, la Acequia, la Sultana; y disfrutando de sus jardines, de Partal, de la Medina y por suspuesto del Generalife con sus graciosos juegos de agua, y su laberíntico diseño.</p>\r\n<p>Incluye además: Recogida, ida y vuelta, en bus en el hotel, azafata acompañante, entradas al monumento y guía oficial.</p>\r\n<p>Duración: Aproximadamente 3 horas.</p>\r\n<p>Horario: Según la disponibilidad a la hora de la reserva.</p>\r\n<p>Precios: 204 € (Domingo a jueves) ; 224 € (Viernes y sábado).</p>\r\n<p>Release de 48 horas.</p>\r\n<p>Política de cancelación de 72 horas.</p>\r\n<p>La oferta se aplica a un MÁXIMO DE 4 PERSONAS.</p>', 'visalhambra', 220, '../imagenes/Alhambra.png'),
('Asistencia a un partido del Granada C.F.', '<p>Incluye: Asistencia a un partido del primer equipo de fútbol de la ciudad, el Granada C.F., contra el rival que desee, menos Madrid y Barcelona.</p>\r\n<p>El Granada C.F. es una S.A.D. fundada hace más de 80 años y que actualmente milita en la Primera División Española, junto a equipos como el Real Madrid, el Atlético o el F.C. Barcelona. A lo largo de su historia ha conseguido la sexta posición en dos ocasiones y ha sido subcampeón de la Copa del Rey. Juega en el Estadio Nuevo los Cármenes, con capacidad para 23.000 espectadores, y tiene unos 11.500 abonados.</p>\r\n<p>Incluye además: Recogida, ida y vuelta, en bus en el hotel.</p>\r\n<p>Duración total: Aproximadamente 3 horas (Incluyendo los 90 minutos del partido).</p>\r\n<p>Horario: Depende de los horarios establecidos por la LFP para los partidos del Granada C.F.</p>\r\n<p>Precio: 15 € (bus) + Precio Preferencia Sector B del partido elegido.</p>\r\n<p>Release de 24 horas.</p>\r\n<p>Política de cancelación de 48 horas.</p>\r\n<p>La oferta se aplica a un MÁXIMO DE 4 PERSONAS.</p>', 'visgranadacf', 45, '../imagenes/GranadaCF.jpg'),
('Visita a Sierra Nevada', '<p>Incluye: 2 noches de alojamiento en habitaciones estándar, desayuno y cena y forfait para dos días en la Estación de Esquí.</p>\r\n<p>Sierra Nevada, Estación de Esquí y Montaña de Andalucía, se encuentra en el Sistema Penibético, en el entorno del Espacio Natural de Sierra Nevada. Con una ubicación privilegiada, en un nudo de conexiones con el resto de provincias andaluzas, dista 31 kilómetros de Granada capital y menos de 100 kilómetros de la Costa Tropical de Granada, y la Estación de Esquí tiene pistas de todos los tipos y más de 100 kilómetros esquiables.</p>\r\n<p>Incluye además: Recogida, ida y vuelta, en bus en el hotel, y clases de esquí de nivel Principiante-Medio.</p>\r\n<p>Duración de las clases: Aproximadamente 6 horas (3 por la mañana y 3 por la tarde).</p>\r\n<p>Horario: Disponible únicamente en fin de semana (Viernes a domingo). Las clases serán de 10:00h-13:00h y de 16:00h-19:00h.</p>\r\n<p>Precio: 270 €.</p>\r\n<p>Release de 48 horas.</p>\r\n<p>Política de cancelación de 72 horas.</p>\r\n<p>La oferta se aplica a un MÁXIMO DE 3 PERSONAS.</p>', 'vissierra', 270, '../imagenes/SierraNevada.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitaciones`
--

CREATE TABLE `habitaciones` (
  `tipohabitacion` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `numhabitacion` int(11) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `preciobase` float NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci NOT NULL,
  `altertext` text COLLATE utf8_spanish_ci NOT NULL,
  `urlimagen` text COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `habitaciones`
--

INSERT INTO `habitaciones` (`tipohabitacion`, `numhabitacion`, `capacidad`, `preciobase`, `descripcion`, `altertext`, `urlimagen`) VALUES
('Individual', 1, 1, 35, '<p>En nuestras habitaciones individuales disfrutará de todas las comodidades para que pueda tener una relajada experiencia en solitario.</p>\r\n\r\n<p>Servicios ofertados: Teléfono, Amenities en el baño, Conexión Wi-Fi a Internet, Aire acondicionado, TV vía satélite, Habitaciones conectadas, Adaptadas a personas con movilidad reducida, Cunas (bajo petición), Secador de pelo, Caja de seguridad.</p>\r\n\r\n<p>Precios: 35.00€ (Alojamiento) - 50.00€ (Alojamiento y desayuno)</p>\r\n\r\n<p>Condiciones especiales: Cancelación sin penalización hasta 24h antes de la entrada del cliente, después de ese período o caso de no-show el hotel podrá facturar la primera noche de la reserva.</p>', 'habindiv', '../imagenes/Individual.jpg'),
('Individual', 2, 1, 35, '', '', '../imagenes/Individual.jpg'),
('Individual', 3, 1, 35, '', '', '../imagenes/Individual.jpg'),
('Individual', 4, 1, 35, '', '', '../imagenes/Individual.jpg'),
('Individual', 5, 1, 35, '', '', '../imagenes/Individual.jpg'),
('Suite', 1, 2, 150, '<p>Goce de las mejores comodidades del hotel en pareja en nuestra particular Suite.</p>\r\n\r\n<p>Servicios ofertados: Teléfono, Amenities en el baño, Conexión Wi-Fi a Internet, Aire acondicionado, TV vía satélite, Habitaciones conectadas, Adaptadas a personas con movilidad reducida, Cunas (bajo petición), Secador de pelo, Caja de seguridad.</p>\r\n\r\n<p>Precios: 150.00€ (Alojamiento) - 165.00€ (Alojamiento y desayuno)</p>\r\n\r\n<p>Condiciones especiales: Cancelación sin penalización hasta 24h antes de la entrada del cliente, después de ese período o caso de no-show el hotel podrá facturar la primera noche de la reserva.</p>', 'habsuite', '../imagenes/Suite.jpg'),
('Superior', 1, 2, 70, '<p>Disfrute de una magnífica vista de Plaza Nueva y el centro de Granada desde nuestras habitaciones superiores.</p>\r\n\r\n<p>Servicios ofertados: Teléfono, Amenities en el baño, Conexión Wi-Fi a Internet, Aire acondicionado, TV vía satélite, Habitaciones conectadas, Adaptadas a personas con movilidad reducida, Cunas (bajo petición), Secador de pelo, Caja de seguridad.</p>\r\n\r\n<p>Precios: 70.00€ (Alojamiento) - 85.00€ (Alojamiento y desayuno)</p>\r\n\r\n<p>Condiciones especiales: Cancelación sin penalización hasta 24h antes de la entrada del cliente, después de ese período o caso de no-show el hotel podrá facturar la primera noche de la reserva.</p>', 'habsuperior', '../imagenes/DobleSuperior.png'),
('Triple', 1, 3, 90, '<p>En nuestras habitaciones triples podrá disfrutar de sus vacaciones en familia o con amigos en el centro de Granada.</p>\r\n\r\n<p>Servicios ofertados: Teléfono, Amenities en el baño, Conexión Wi-Fi a Internet, Aire acondicionado, TV vía satélite, Habitaciones conectadas, Adaptadas a personas con movilidad reducida, Cunas (bajo petición), Secador de pelo, Caja de seguridad.</p>\r\n\r\n<p>Precios: 90.00€ (Alojamiento) - 105.00€ (Alojamiento y desayuno)</p>\r\n\r\n<p>Condiciones especiales: Cancelación sin penalización hasta 24h antes de la entrada del cliente, después de ese período o caso de no-show el hotel podrá facturar la primera noche de la reserva.</p>', 'habtriple', '../imagenes/Triple.jpg'),
('Twin', 1, 2, 50, '<p>En nuestras habitaciones standard disfrutará de todo el equipamiento y comodidades que su estancia en Granada merece.</p>\r\n\r\n<p>Servicios ofertados: Teléfono, Amenities en el baño, Conexión Wi-Fi a Internet, Aire acondicionado, TV vía satélite, Habitaciones conectadas, Adaptadas a personas con movilidad reducida, Cunas (bajo petición), Secador de pelo, Caja de seguridad.</p>\r\n\r\n<p>Precios: 50.00€ (Alojamiento) - 65.00€ (Alojamiento y desayuno)</p>\r\n\r\n<p>Condiciones especiales: Cancelación sin penalización hasta 24h antes de la entrada del cliente, después de ese período o caso de no-show el hotel podrá facturar la primera noche de la reserva.</p>', 'habtwin', '../imagenes/DobleTwin.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `CIF` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `localidad` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `provincia` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `pais` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `googlemaps` text COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(35) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `fax` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci NOT NULL,
  `infocontacto` text COLLATE utf8_spanish_ci NOT NULL,
  `facebook` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `twitter` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`CIF`, `direccion`, `localidad`, `provincia`, `pais`, `googlemaps`, `nombre`, `email`, `telefono`, `fax`, `descripcion`, `infocontacto`, `facebook`, `twitter`) VALUES
('674589231', 'Imprenta, nº 2', 'Granada', 'Granada', 'España', 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1589.4879384119486!2d-3.597400708345436!3d37.17704425399749!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd71fcb8cb9390e1%3A0x9253186efccf153a!2sHotel+Plaza+Nueva!5e0!3m2!1ses!2ses!4v1457088786880', 'Hotel Plaza Nueva * * *', 'contactohotelplazanueva@gmail.com', '+34 958 21 52 73', '+43 958 22 57 65', '<div id="content">\r\n<div id="normalcontent">\r\n<h3>BIENVENIDO AL HOTEL PLAZA NUEVA</h3>\r\n<br>\r\n<h4>Descubra nuestras mejores ofertas y promociones:</h4>\r\n</div>\r\n\r\n<div id="primarycontainer">\r\n<div id="primarycontent">\r\n<hr>\r\n<img src="imagenes/hotel_miniatura.jpg" class="left" alt="Miniatura del hotel" width=200px height=220px/>\r\n<p><b>El Hotel Plaza Nueva está situado en el pleno centro monumental, comercial y administrativo de Granada, a 10 minutos de la Alhambra.</b></p>\r\n<p>El hotel ofrece una amplia y eficiente gama de servicios extra que satisfarán cualquier necesidad que le surja, reservas a shows de flamenco o visitas turísticas por la ciudad y la Alhambra.</p>\r\n<p>El hotel le ofrece 25 amplias y luminosas habitaciones repartidas sobre 3 plantas con ascensor.</p>\r\n<p>Cada planta del hotel y cada habitación poseen un nombre y encanto propio. Este nombre es una representación de un evento importante en la vida e historia de Granada. No tienen tarjetas magnéticas para abrir las puertas de las habitaciones, preferimos la originalidad que proporciona una tradicional llave.</p>\r\n<p>El hotel ofrece el servicio de desayuno continental en la cafetería, donde podrá disfrutar de conexión WIFI. Asimismo podrá obtener mediante pago del servicio conexión WIFI en su habitación.</p>\r\n<p>Confiamos en que disfrute plenamente su estancia entre nosotros así como de nuestra bella ciudad.</p>\r\n<p>Número de registro del hotel en la consejería de turismo de Andalucía: H/GR/01181.</p>\r\n<hr>\r\n<h3>Promociones</h3>\r\n<div class="contentarea">\r\n<p>Descubre las mejores propuestas para sacar el máximo partido a tu estancia en Granada. El Hotel Plaza Nueva ofrece una amplia variedad de actividades lúdicas, haga de su estancia una estancia inolvidable.</p>\r\n<a href="index.php?seccion=Promociones" class="more">MÁS INFORMACIÓN</a>\r\n</div>\r\n<hr>\r\n\r\n<h3>Contacto</h3>\r\n<div class="contentarea">\r\n<p>Conozca toda la información necesaria para poder localizarnos y llegar a nosotros sin problemas.</p>\r\n<a href="index.php?seccion=Contacto" class="more">MÁS INFORMACIÓN</a>\r\n</div>\r\n<hr>\r\n</div>\r\n</div>\r\n</div>', '<p>Para llegar al Hotel Plaza Nueva toma el Anillo de Granada, dirección Motril-Sierra Nevada, y coge la salida número 128: Centro-Méndez Núñez. Continúa por la avenida Fuente Nueva-Severo Ochoa y, sin tomar el túnel, gira a la derecha en la Avenida de la Constitución.</p>\r\n<p>Al final hay una rotonda con una bandera de España. Gira a la izquierda en la Avenida del Hospicio (deja los Jardines del Triunfo a tu izquierda) y gira a la derecha (hay un semáforo en la intersección), Avenida Capitán Moreno.</p>\r\n<p>Sigue recto hasta el final de la avenida y en la rotonda continúa recto. Luego gira a la izquierda (justo enfrente de un hotel) y pasa bajo el Arco o Puerta Elvira. Continúa por la calle Elvira. Hacia el final de la calle verás señales luminosas. Continúa recto y verás un kiosco. Al final de la calle Pan se encuentra la entrada principal del Hotel Plaza Nueva. Se puede llegar hasta la puerta del hotel para descargar el equipaje.</p>\r\n<p>Si has reservado aparcamiento, situado a sólo 100 metros del hotel, te acompañaremos. La tarifa de aparcamiento es de 20,00 € al día y la hora de salida es a las 12:00. Recuerda que si cancelas el estacionamiento en el día de llegada, debes pagar el 50% del importe de la tarifa, ya que las plazas son limitadas.</p>', 'https://www.facebook.com/Hotel-Plaza-Nueva-176542882374100/?fref=ts', 'https://twitter.com/HOTELPLAZANUEVA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menudinamico`
--

CREATE TABLE `menudinamico` (
  `orden` int(11) NOT NULL,
  `idmenu` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `linkhref` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `padre` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `menudinamico`
--

INSERT INTO `menudinamico` (`orden`, `idmenu`, `linkhref`, `padre`) VALUES
(0, 'Principal', '', ''),
(4, 'Contacto', 'index.php?seccion=Contacto', 'Principal'),
(2, 'Habitaciones', 'index.php?seccion=Habitaciones', 'Principal'),
(1, 'Habitación Individual', 'index.php?seccion=Habitaciones&tipo=#habindiv', 'Habitaciones'),
(5, 'Habitación Suite', 'index.php?seccion=Habitaciones&tipo=#habsuite', 'Habitaciones'),
(3, 'Habitación Doble Superior', 'index.php?seccion=Habitaciones&tipo=#habsuperior', 'Habitaciones'),
(4, 'Habitación Triple', 'index.php?seccion=habitaciones&tipo=#habtriple', 'Habitaciones'),
(2, 'Habitación Doble Twin', 'index.php?seccion=Habitaciones&tipo=#habtwin', 'Habitaciones'),
(1, 'Inicio', 'index.php?seccion=Inicio', 'Principal'),
(3, 'Promociones', 'index.php?seccion=Promociones', 'Principal'),
(1, 'Descuento del 10%', 'index.php?seccion=Promociones&tipo=#descuento10', 'Promociones'),
(2, 'Visita a la Alhambra', 'index.php?seccion=Promociones&tipo=#visalhambra', 'Promociones'),
(4, 'Descuento en partido del Granada C.F.', 'index.php?seccion=Promociones&tipo=#visgranadacf', 'Promociones'),
(3, 'Visita a Sierra Nevada', 'index.php?seccion=Promociones&tipo=#vissierra', 'Promociones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones`
--

CREATE TABLE `promociones` (
  `codigopromocion` varchar(32) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci NOT NULL,
  `altertext` varchar(32) COLLATE utf8_spanish_ci NOT NULL,
  `descuento` int(32) NOT NULL,
  `urlimg` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `promociones`
--

INSERT INTO `promociones` (`codigopromocion`, `nombre`, `descripcion`, `altertext`, `descuento`, `urlimg`) VALUES
('10DESC', 'Descuento del 10% en una reserva', '<p>Disfrute de un 10% de descuento en cualquiera de nuestras habitaciones.</p>\r\n\r\n<p>Con la reserva de una habitación Triple junto a nuestra actividad de asistencia a un partido del Granada C.F., usted recibirá un 10% de descuento en el precio final de la reserva (porque sabemos que ir con la familia a un partido de fútbol siempre sale caro).</p>\r\n\r\n<p>Release de 48 horas.</p>\r\n\r\n<p>Política de cancelación de 72 horas.</p>\r\n\r\n<p>Disponible hasta el fin de la temporada de fútbol actual.</p>', 'descuento10', 10, './imagenes/Descuento10.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `requisitopromocion`
--

CREATE TABLE `requisitopromocion` (
  `tipohabitacion` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `nombreactividad` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `codigopromocion` varchar(32) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `requisitopromocion`
--

INSERT INTO `requisitopromocion` (`tipohabitacion`, `nombreactividad`, `codigopromocion`) VALUES
('Triple', 'visgranadacf', '10DESC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservahabitacion`
--

CREATE TABLE `reservahabitacion` (
  `localizador` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `tipohabitacion` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `numhabitacion` int(11) NOT NULL,
  `nombreactividad` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombreservicio` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `reservahabitacion`
--

INSERT INTO `reservahabitacion` (`localizador`, `tipohabitacion`, `numhabitacion`, `nombreactividad`, `nombreservicio`) VALUES
('q2w3e4r5', 'Individual', 1, 'visalhambra', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `localizador` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `fechaentrada` date NOT NULL,
  `fechasalida` date NOT NULL,
  `comentario` text COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`localizador`, `fechaentrada`, `fechasalida`, `comentario`) VALUES
('q2w3e4r5', '2016-05-28', '2016-05-29', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `nombreservicio` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci NOT NULL,
  `altertext` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `precio` float NOT NULL,
  `urlimagen` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`nombreservicio`, `descripcion`, `altertext`, `precio`, `urlimagen`) VALUES
('Desayuno', 'Por sólo 15 euros más, le llevamos a su habitación un exquisito desayuno consistente en una taza de café con leche, tostadas (de los ingredientes que usted nos indique en recepción) y un zumo de naranja 100% natural', 'servdesayuno', 15, '../Imagenes/Desayuno.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `tipouusario` varchar(32) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'registrado',
  `nombre` varchar(32) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `dni` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `correo` varchar(32) COLLATE utf8_spanish_ci NOT NULL,
  `numtarjeta` int(32) DEFAULT NULL,
  `password` varchar(32) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`tipouusario`, `nombre`, `apellidos`, `dni`, `correo`, `numtarjeta`, `password`, `telefono`) VALUES
('administrador', 'borch', 'canavate', '45166540C', 'canavate100@gmail.com', NULL, '123456', 675432123);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividades`
--
ALTER TABLE `actividades`
  ADD PRIMARY KEY (`altertext`);

--
-- Indices de la tabla `habitaciones`
--
ALTER TABLE `habitaciones`
  ADD PRIMARY KEY (`tipohabitacion`,`numhabitacion`);

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`CIF`);

--
-- Indices de la tabla `menudinamico`
--
ALTER TABLE `menudinamico`
  ADD PRIMARY KEY (`linkhref`);

--
-- Indices de la tabla `promociones`
--
ALTER TABLE `promociones`
  ADD PRIMARY KEY (`codigopromocion`);

--
-- Indices de la tabla `requisitopromocion`
--
ALTER TABLE `requisitopromocion`
  ADD PRIMARY KEY (`tipohabitacion`,`nombreactividad`);

--
-- Indices de la tabla `reservahabitacion`
--
ALTER TABLE `reservahabitacion`
  ADD PRIMARY KEY (`localizador`,`tipohabitacion`,`numhabitacion`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`localizador`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`nombreservicio`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`dni`),
  ADD UNIQUE KEY `correo` (`correo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
