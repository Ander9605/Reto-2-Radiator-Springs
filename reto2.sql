-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-02-2025 a las 12:02:04
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reto2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `id_empleado` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contrasena` varchar(50) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `puesto` varchar(50) DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `activo` tinyint(1) DEFAULT 1,
  `horas_totales` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`id_empleado`, `nombre`, `apellido`, `email`, `contrasena`, `telefono`, `puesto`, `fecha_alta`, `activo`, `horas_totales`) VALUES
(1, 'Pepe', 'Pérez', 'pepe.perez@gmail.com', '1234', '655123456', 'gerente', '2024-02-09', 1, 0),
(2, 'Juan', 'Gonzalez', 'juan.gonzalez@gmail.com', '4321', '652785567', 'empleado', '2024-02-09', 1, 12),
(3, 'Fernando', 'Martinez', 'fernando.caza@gmail.com', '0000', '646783211', 'empleado', '2024-02-10', 1, 0),
(4, 'Ander', 'Perez', 'ander.perez@gmail.com', '1111', '645789900', 'gerente', '2024-01-02', 1, 0),
(5, 'Erlantz', 'Urbaneja', 'erlantz.urbaneja@gmail.com', '2222', '657893344', 'empleado', '2024-03-23', 1, 6),
(6, 'Aketx', 'Cabornero', 'aketx.cabornero@gmail.com', '3333', '678443373', 'empleado', '2024-04-11', 1, 6),
(7, 'Angel', 'Milinai', 'angel.miliani', '4444', '654889932', 'empleado', '2024-12-11', 1, 6),
(8, 'Enrique', 'Garcia', 'enrique.garcia@gmail.com', '5678', '655523156', 'empleado', '2024-11-11', 0, 0),
(9, 'Maria', 'Lopez', 'maria.lopez@gmail.com', '9109', '655345678', 'empleado', '2024-02-07', 1, 0),
(10, 'Carlos', 'Sanchez', 'carlos.sanchez@gmail.com', '1121', '656081122', 'empleado', '2024-02-06', 1, 0),
(11, 'Eva', 'Fernandez', 'eva.fernandez@gmail.com', '3141', '678998800', 'empleado', '2024-03-22', 1, 6),
(12, 'Sergio', 'Ramirez', 'sergio.ramirez@gmail.com', '5566', '687000021', 'empleado', '2024-08-01', 1, 12),
(13, 'Jorge', 'Rivas', 'jorge.rivas@gmail.com', '7282', '678444421', 'empleado', '2024-07-09', 1, 6),
(14, 'Beatriz', 'Herrera', 'beatriz.herrera@gmail.com', '2229', '656123222', 'empleado', '2022-01-23', 0, 0),
(15, 'Elena', 'Torres', 'elena.torres@gmail.com', '9898', '675332301', 'empleado', '2024-07-09', 1, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarios`
--

CREATE TABLE `horarios` (
  `id_horario` int(11) NOT NULL,
  `id_empleado` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora_entrada` time DEFAULT NULL,
  `hora_salida` time DEFAULT NULL,
  `horas_trabajadas` decimal(5,2) GENERATED ALWAYS AS (timestampdiff(MINUTE,`hora_entrada`,`hora_salida`) / 60) STORED
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `horarios`
--

INSERT INTO `horarios` (`id_horario`, `id_empleado`, `fecha`, `hora_entrada`, `hora_salida`) VALUES
(1, 2, '2025-02-09', '15:00:00', '21:00:00'),
(2, 2, '2025-01-10', '15:00:00', '21:00:00'),
(3, 6, '2025-02-02', '15:00:00', '21:00:00'),
(4, 15, '2024-04-11', '15:00:00', '21:00:00'),
(5, 7, '2024-09-11', '15:00:00', '21:00:00'),
(6, 13, '2024-11-10', '15:00:00', '21:00:00'),
(7, 12, '2024-12-23', '15:00:00', '21:00:00'),
(8, 5, '2024-10-11', '15:00:00', '21:00:00'),
(9, 12, '2024-09-01', '15:00:00', '21:00:00'),
(10, 11, '2024-10-23', '15:00:00', '21:00:00');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id_empleado`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `horarios`
--
ALTER TABLE `horarios`
  ADD PRIMARY KEY (`id_horario`),
  ADD KEY `id_empleado` (`id_empleado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `id_empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `horarios`
--
ALTER TABLE `horarios`
  MODIFY `id_horario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `horarios`
--
ALTER TABLE `horarios`
  ADD CONSTRAINT `horarios_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
