-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-03-2023 a las 16:55:14
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `rutaturisticafxmiguel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carnets`
--

CREATE TABLE `carnets` (
  `id_carnet` bigint(20) NOT NULL,
  `distancia` double DEFAULT NULL,
  `fechaexp` date DEFAULT NULL,
  `numvips` int(11) DEFAULT NULL,
  `id_parada` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `carnets`
--

INSERT INTO `carnets` (`id_carnet`, `distancia`, `fechaexp`, `numvips`, `id_parada`) VALUES
(1, 300, '2023-03-13', 0, 3),
(2, 100, '2023-03-13', 0, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estancias`
--

CREATE TABLE `estancias` (
  `id_estancia` bigint(20) NOT NULL,
  `fecha` date DEFAULT NULL,
  `vip` bit(1) DEFAULT NULL,
  `id_parada` bigint(20) DEFAULT NULL,
  `id_peregrino` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `estancias`
--

INSERT INTO `estancias` (`id_estancia`, `fecha`, `vip`, `id_parada`, `id_peregrino`) VALUES
(1, '2023-03-13', b'0', 4, 1),
(2, '2023-03-15', b'1', 3, 1),
(3, '2023-03-17', b'0', 2, 1),
(4, '2023-03-17', b'0', 3, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paradas`
--

CREATE TABLE `paradas` (
  `id_parada` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `region` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `paradas`
--

INSERT INTO `paradas` (`id_parada`, `nombre`, `region`) VALUES
(1, 'Bilbao', 'V'),
(2, 'Santander', 'C'),
(3, 'Cangas de Onis', 'A'),
(4, 'Oviedo', 'A'),
(5, 'Lugo', 'G');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peregrinos`
--

CREATE TABLE `peregrinos` (
  `id_peregrino` bigint(20) NOT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_carnet` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `peregrinos`
--

INSERT INTO `peregrinos` (`id_peregrino`, `nacionalidad`, `nombre`, `id_carnet`) VALUES
(1, 'España', 'miguel', 1),
(2, 'Andorra', 'Borja', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peregrinos_paradas`
--

CREATE TABLE `peregrinos_paradas` (
  `peregrinos_id_peregrino` bigint(20) NOT NULL,
  `paradas_id_parada` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `peregrinos_paradas`
--

INSERT INTO `peregrinos_paradas` (`peregrinos_id_peregrino`, `paradas_id_parada`) VALUES
(1, 3),
(1, 4),
(1, 3),
(1, 2),
(2, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE `perfil` (
  `id_perfil` bigint(20) NOT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `perfil`
--

INSERT INTO `perfil` (`id_perfil`, `rol`) VALUES
(1, 'peregrino'),
(2, 'parada'),
(3, 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` bigint(20) NOT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `parada_id_parada` bigint(20) DEFAULT NULL,
  `peregrino_id_peregrino` bigint(20) DEFAULT NULL,
  `id_perfil` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `pass`, `user`, `parada_id_parada`, `peregrino_id_peregrino`, `id_perfil`) VALUES
(3, 'miguel123', 'miguelh', NULL, 1, 1),
(4, 'admin', 'admin', NULL, NULL, 3),
(5, 'cangas', 'admincangas', 3, NULL, 2),
(6, 'santander', 'adminsantander', 2, NULL, 2),
(7, 'borja123', 'borjamarin', NULL, 2, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carnets`
--
ALTER TABLE `carnets`
  ADD PRIMARY KEY (`id_carnet`),
  ADD KEY `FKjppmyy6tlhqs6ogeve14cjb1s` (`id_parada`);

--
-- Indices de la tabla `estancias`
--
ALTER TABLE `estancias`
  ADD PRIMARY KEY (`id_estancia`),
  ADD KEY `FKi1fam1r85nkth28g6kjlg08rf` (`id_parada`),
  ADD KEY `FKds6ch16adx2qcy06s5pax58j6` (`id_peregrino`);

--
-- Indices de la tabla `paradas`
--
ALTER TABLE `paradas`
  ADD PRIMARY KEY (`id_parada`);

--
-- Indices de la tabla `peregrinos`
--
ALTER TABLE `peregrinos`
  ADD PRIMARY KEY (`id_peregrino`),
  ADD KEY `FKa4r8o609006xdsgb7rshiwhco` (`id_carnet`);

--
-- Indices de la tabla `peregrinos_paradas`
--
ALTER TABLE `peregrinos_paradas`
  ADD KEY `FKhtk8flnsrst9f8j5c23m5cxw2` (`paradas_id_parada`),
  ADD KEY `FKcjcytad7fekc0x05ik7hamfc4` (`peregrinos_id_peregrino`);

--
-- Indices de la tabla `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`id_perfil`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `FKogb847vky0g0e37r851od041` (`parada_id_parada`),
  ADD KEY `FKay68dumt8r9jgn07i49bqjcgh` (`peregrino_id_peregrino`),
  ADD KEY `FK5uhgf0n3l7xf0pohnlktswf4s` (`id_perfil`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carnets`
--
ALTER TABLE `carnets`
  MODIFY `id_carnet` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `estancias`
--
ALTER TABLE `estancias`
  MODIFY `id_estancia` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `paradas`
--
ALTER TABLE `paradas`
  MODIFY `id_parada` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `peregrinos`
--
ALTER TABLE `peregrinos`
  MODIFY `id_peregrino` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id_perfil` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carnets`
--
ALTER TABLE `carnets`
  ADD CONSTRAINT `FKjppmyy6tlhqs6ogeve14cjb1s` FOREIGN KEY (`id_parada`) REFERENCES `paradas` (`id_parada`);

--
-- Filtros para la tabla `estancias`
--
ALTER TABLE `estancias`
  ADD CONSTRAINT `FKds6ch16adx2qcy06s5pax58j6` FOREIGN KEY (`id_peregrino`) REFERENCES `peregrinos` (`id_peregrino`),
  ADD CONSTRAINT `FKi1fam1r85nkth28g6kjlg08rf` FOREIGN KEY (`id_parada`) REFERENCES `paradas` (`id_parada`);

--
-- Filtros para la tabla `peregrinos`
--
ALTER TABLE `peregrinos`
  ADD CONSTRAINT `FKa4r8o609006xdsgb7rshiwhco` FOREIGN KEY (`id_carnet`) REFERENCES `carnets` (`id_carnet`);

--
-- Filtros para la tabla `peregrinos_paradas`
--
ALTER TABLE `peregrinos_paradas`
  ADD CONSTRAINT `FKcjcytad7fekc0x05ik7hamfc4` FOREIGN KEY (`peregrinos_id_peregrino`) REFERENCES `peregrinos` (`id_peregrino`),
  ADD CONSTRAINT `FKhtk8flnsrst9f8j5c23m5cxw2` FOREIGN KEY (`paradas_id_parada`) REFERENCES `paradas` (`id_parada`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `FK5uhgf0n3l7xf0pohnlktswf4s` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id_perfil`),
  ADD CONSTRAINT `FKay68dumt8r9jgn07i49bqjcgh` FOREIGN KEY (`peregrino_id_peregrino`) REFERENCES `peregrinos` (`id_peregrino`),
  ADD CONSTRAINT `FKogb847vky0g0e37r851od041` FOREIGN KEY (`parada_id_parada`) REFERENCES `paradas` (`id_parada`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
