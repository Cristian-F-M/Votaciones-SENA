-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-09-2023 a las 14:16:15
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_formativo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `idAdministrador` int(11) NOT NULL,
  `nombreAdministrador` varchar(45) DEFAULT NULL,
  `tipoDocumentoAdministrador` int(11) NOT NULL,
  `documentoAdministrador` varchar(50) NOT NULL,
  `correoAdministrador` varchar(50) NOT NULL,
  `contraseñaAdministrador` varbinary(256) NOT NULL,
  `rolAdministrador` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`idAdministrador`, `nombreAdministrador`, `tipoDocumentoAdministrador`, `documentoAdministrador`, `correoAdministrador`, `contraseñaAdministrador`, `rolAdministrador`) VALUES
(1, 'Cristian Morales', 1, '1101752630', 'cforales.diaz@gmail.com', 0x243261243130242f354b44636e4653382f4a7a764f6a32373859674165627965556b652e42556f6447423156764b386c6a6d4c312f4652356b66362e, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aprendiz`
--

CREATE TABLE `aprendiz` (
  `idAprendiz` int(11) NOT NULL,
  `nombreAprendiz` varchar(45) NOT NULL,
  `tipoDocumentoAprendiz` int(11) DEFAULT NULL,
  `documentoAprendiz` varchar(45) NOT NULL,
  `correoAprendiz` varchar(45) NOT NULL,
  `contraseñaAprendiz` varbinary(256) DEFAULT NULL,
  `candidato` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `aprendiz`
--

INSERT INTO `aprendiz` (`idAprendiz`, `nombreAprendiz`, `tipoDocumentoAprendiz`, `documentoAprendiz`, `correoAprendiz`, `contraseñaAprendiz`, `candidato`) VALUES
(1, 'Usuario de prueba', 1, '1', 'q@q', 0x24326124313024476e665779697a36376830714a63356d4c7a596d542e4a41702f6365714b44626145465352676a7a684a752e687a6d506d53355475, NULL),
(2, 'Cristian Fernando Morales Díaz', 1, '1101752630', 'cfmorales.diaz@gmail.com', 0x24326124313024554a51774675596a59645a6e4e63356c627a62353165524a65482e424b34716e46555569497757445978344f4d6a42414c4f595153, 2),
(3, 'Laura Morales', 1, '1007477797', 'laurittamorales20@gmail.com', 0x243261243130244b783274384c705a643577455a4866484e774e3974656e7454324244482e51466865754655484e684f6a4c2f475572633955437371, NULL),
(4, 'Cjfkjdsn', 1, '12345678', 'a@a', 0x2432612431302458332e677431356f6278592f676d643141386f52532e71697332645855454d7035364a666c34484c4e5266742e2f352e6876775865, NULL),
(5, 'Díaz más', 1, '123456789', 'a@aa', 0x243261243130244d786a6d574f6377763646366b7a715a727a2e74574f34686239554c656554657035336f567842645841374e493952426473576e79, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria`
--

CREATE TABLE `auditoria` (
  `idAuditoria` int(11) NOT NULL,
  `detalleAuditoria` varchar(70) NOT NULL,
  `nombreUsuarioAuditoria` varchar(45) DEFAULT NULL,
  `fechaAuditoria` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `auditoria`
--

INSERT INTO `auditoria` (`idAuditoria`, `detalleAuditoria`, `nombreUsuarioAuditoria`, `fechaAuditoria`) VALUES
(58, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 01:36:57'),
(59, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 01:37:36'),
(60, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 01:38:28'),
(61, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 01:42:12'),
(62, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 01:42:22'),
(63, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 01:46:14'),
(64, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 01:46:37'),
(65, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 13:05:07'),
(66, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 13:05:42'),
(67, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 13:06:57'),
(68, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 13:07:32'),
(69, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 13:09:13'),
(70, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 13:10:19'),
(71, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 13:11:03'),
(72, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 13:12:08'),
(73, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 13:13:08'),
(74, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 13:15:29'),
(75, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 13:33:06'),
(76, 'Aprendiz Insertado', 'Aprendiz', '2023-09-12 13:34:51'),
(77, 'Aprendiz Insertado', 'Aprendiz', '2023-09-13 20:07:45'),
(78, 'Aprendiz Insertado', 'Aprendiz', '2023-09-13 20:08:49'),
(79, 'Aprendiz Insertado', 'Aprendiz', '2023-09-13 20:09:23'),
(80, 'Aprendiz Insertado', 'Aprendiz', '2023-09-13 20:41:30'),
(81, 'Aprendiz Insertado', 'Aprendiz', '2023-09-13 22:42:19'),
(82, 'Aprendiz Insertado', 'Aprendiz', '2023-09-13 22:52:42'),
(83, 'Aprendiz Insertado', 'Aprendiz', '2023-09-13 22:53:44'),
(84, 'Aprendiz Insertado', 'Aprendiz', '2023-09-19 12:23:15'),
(85, 'Administrador Insertado', 'Owner', '2023-09-20 00:47:13'),
(86, 'Administrador Insertado', 'Owner', '2023-09-20 16:23:19'),
(87, 'Administrador Insertado', 'Owner', '2023-09-20 16:23:35'),
(88, 'Administrador Insertado', 'Owner', '2023-09-20 16:24:03'),
(89, 'Administrador Insertado', 'Owner', '2023-09-20 16:24:16'),
(90, 'Administrador Insertado', 'Owner', '2023-09-20 16:25:11'),
(91, 'Administrador Insertado', 'Owner', '2023-09-20 16:25:23'),
(92, 'Administrador Insertado', 'Owner', '2023-09-20 16:28:32'),
(93, 'Administrador Insertado', 'Owner', '2023-09-20 16:28:43'),
(94, 'Administrador Insertado', 'Owner', '2023-09-20 16:29:05'),
(95, 'Administrador Insertado', 'Owner', '2023-09-20 16:29:18'),
(96, 'Administrador Insertado', 'Owner', '2023-09-20 16:39:21'),
(97, 'Aprendiz Insertado', 'Aprendiz', '2023-09-20 23:49:02');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `candidato`
--

CREATE TABLE `candidato` (
  `idCandidato` int(11) NOT NULL,
  `aprendiz` int(11) NOT NULL,
  `descripcionCandidato` varchar(150) DEFAULT NULL,
  `fotoCandidato` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `candidato`
--

INSERT INTO `candidato` (`idCandidato`, `aprendiz`, `descripcionCandidato`, `fotoCandidato`) VALUES
(1, 2, 'Ficha: 2557615 Análisis y desarrollo de software', 'archivos/images/Foto.jpg'),
(2, 1, 'Usuario de prueba', 'archivos/images/Triste!.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL,
  `descripcionRol` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idRol`, `descripcionRol`) VALUES
(1, 'Owner'),
(2, 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipodocumento`
--

CREATE TABLE `tipodocumento` (
  `idTipoDocumento` int(11) NOT NULL,
  `descripcionTipoDocumento` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `tipodocumento`
--

INSERT INTO `tipodocumento` (`idTipoDocumento`, `descripcionTipoDocumento`) VALUES
(1, 'Cédula de ciudadanía'),
(2, 'Tarjeta de identidad'),
(3, 'Cédula de extranjeria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `votacion`
--

CREATE TABLE `votacion` (
  `idVotacion` int(11) NOT NULL,
  `fechaInicioVotacion` date DEFAULT NULL,
  `fechaFinVotacion` date DEFAULT NULL,
  `cantVotosVotacion` varchar(45) DEFAULT NULL,
  `ganadorVotacion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `votacion`
--

INSERT INTO `votacion` (`idVotacion`, `fechaInicioVotacion`, `fechaFinVotacion`, `cantVotosVotacion`, `ganadorVotacion`) VALUES
(1, '2023-07-23', '2023-07-31', '1', '1'),
(2, '2023-09-19', '2023-09-21', NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`idAdministrador`),
  ADD KEY `tipoDocumentoAdministrador` (`tipoDocumentoAdministrador`),
  ADD KEY `rolAdministrador` (`rolAdministrador`);

--
-- Indices de la tabla `aprendiz`
--
ALTER TABLE `aprendiz`
  ADD PRIMARY KEY (`idAprendiz`),
  ADD KEY `tipoDocumentoAprendiz` (`tipoDocumentoAprendiz`);

--
-- Indices de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  ADD PRIMARY KEY (`idAuditoria`);

--
-- Indices de la tabla `candidato`
--
ALTER TABLE `candidato`
  ADD PRIMARY KEY (`idCandidato`),
  ADD KEY `aprendiz` (`aprendiz`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idRol`);

--
-- Indices de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  ADD PRIMARY KEY (`idTipoDocumento`);

--
-- Indices de la tabla `votacion`
--
ALTER TABLE `votacion`
  ADD PRIMARY KEY (`idVotacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administrador`
--
ALTER TABLE `administrador`
  MODIFY `idAdministrador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `aprendiz`
--
ALTER TABLE `aprendiz`
  MODIFY `idAprendiz` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  MODIFY `idAuditoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;

--
-- AUTO_INCREMENT de la tabla `candidato`
--
ALTER TABLE `candidato`
  MODIFY `idCandidato` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idRol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  MODIFY `idTipoDocumento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `votacion`
--
ALTER TABLE `votacion`
  MODIFY `idVotacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `administrador_ibfk_1` FOREIGN KEY (`tipoDocumentoAdministrador`) REFERENCES `tipodocumento` (`idTipoDocumento`),
  ADD CONSTRAINT `administrador_ibfk_2` FOREIGN KEY (`rolAdministrador`) REFERENCES `rol` (`idRol`);

--
-- Filtros para la tabla `aprendiz`
--
ALTER TABLE `aprendiz`
  ADD CONSTRAINT `aprendiz_ibfk_1` FOREIGN KEY (`tipoDocumentoAprendiz`) REFERENCES `tipodocumento` (`idTipoDocumento`);

--
-- Filtros para la tabla `candidato`
--
ALTER TABLE `candidato`
  ADD CONSTRAINT `candidato_ibfk_1` FOREIGN KEY (`aprendiz`) REFERENCES `aprendiz` (`idAprendiz`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
