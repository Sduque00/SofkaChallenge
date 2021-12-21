-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-12-2021 a las 04:20:17
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `answerandquestions`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Programming'),
(4, 'Geography'),
(6, 'Sport'),
(7, 'Math'),
(8, 'History');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `game`
--

CREATE TABLE `game` (
  `id` int(11) NOT NULL,
  `player` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `round` int(11) NOT NULL,
  `status` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `accumulate` double NOT NULL,
  `date` varchar(60) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `id_question` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `game`
--

INSERT INTO `game` (`id`, `player`, `round`, `status`, `accumulate`, `date`, `id_question`) VALUES
(5, 'Pepito', 3, 'Cobarde', 3000, 'Dec 20, 2021 - 0:11', 3),
(6, 'nombre', 2, 'Loser', 1000, 'Dec 20, 2021 - 13:1', 1),
(7, 'Andres', 5, 'Winner', 10000, '20-dic-2021 - 15:55', 5),
(8, 'aaaa', 1, 'Loser', 0, '20-dic-2021 - 15:55', 6),
(9, 'aa', 5, 'Winner', 10000, '20-dic-2021 - 15:56', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `question` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `answer` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `option_1` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `option_2` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `option_3` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `level` int(11) NOT NULL,
  `id_category` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `question`
--

INSERT INTO `question` (`id`, `question`, `answer`, `option_1`, `option_2`, `option_3`, `level`, `id_category`) VALUES
(1, '¿Qué es polimorfismo?', 'Un pilar de POO', 'Un animal', 'Un lenguaje compilado', 'Un framework', 2, 1),
(2, '¿Cuáles son los pilares de POO?', 'Polimorfismo, herencia, encapsulamiento, abstracci', 'Compilado, interpretado', 'Mobile, web', 'Librerías', 1, 1),
(3, '¿Qué es una base de datos?', 'Una herramienta para tener persistencia en datos', 'Un motor', 'Una query ', 'Un conjunto de filas y columnas', 3, 1),
(4, '¿Qué hace un desarrollador frontend?', 'Se encarga del desarrollo UI/UX', 'Trabaja con Java y bases de datos', 'Desarrolla la lógica del servidor', 'Ninguna', 4, 1),
(5, 'int n = (v != true) ? 1 :  0; ¿qué valor tendría n', '1', '0', 'null', 'error', 5, 1),
(6, '¿Cuál es la capital de Colombia?', 'Bogotá', 'Medellín', 'Cali', 'Barrarnquilla', 1, 4),
(7, '¿Cuál es la capital de Alemania?', 'Berlín', 'Londres', 'San Francisco', 'Bonn', 2, 4),
(8, '¿Dónde está ubicado el pentágono?', 'Virginia', 'Nueva York', 'Las Vegas', 'Los Ángeles', 3, 4),
(9, '¿Dónde está ubicada la esfinge?', 'Egipto', 'México', 'Perú', 'Rusia', 4, 4),
(10, '¿Dónde se encuentra La Meseta?', 'Bello', 'Medellín', 'Bogotá', 'Envigado', 5, 4),
(11, '¿Cuánto dura un partido de fútbol?', '90 min', '100 min', '120min', '60min', 1, 6),
(12, '¿Quién se considera el mejor jugador de baloncesto', 'Micheal Jordan', 'Lebron James', 'Jame Harden', 'Kevin Durant', 2, 6),
(13, '¿Cuántos jugadores componen un equipo de rugby?', '15 jugadores', '11 jugadores', '13 jugadores', '10 jugadores', 3, 6),
(15, '¿Qué es un decatlón?', 'Competición del atletismo ', 'Competición de carreras de autos', 'Torneo de futbol ', 'competición de natación ', 4, 6),
(16, '¿Estilo de atletismo en el que los corredores llev', 'Carreras de relevo', 'Carreras en parejas', 'Carreras mixtas', 'Carreras sincronizadas ', 5, 6),
(17, '¿Cuanto es 22+39?', '61', '66', '69', '70', 1, 7),
(18, '¿Cuánto es 32-95?', '-63', '63', '-52', '60', 2, 7),
(19, '¿Cuanto es 46 * 67?', '3082', '3065', '3005', '3216', 3, 7),
(20, '¿Cuánto es 69/23 ?', '3', '6', '12', '23', 4, 7),
(21, '¿Cuánto es (25+5) / 5*55?', '330', '320', '315', '346', 5, 7),
(22, '¿En que año descubrió Colón América?', '1942', '1502', '1496', '1488', 1, 8),
(23, '¿En que guerra participó Juana de Arco?', 'La guerra de los 100 años ', 'Guerras napoleónicas ', 'Primera cruzada ', 'La guerra de los 30 años ', 2, 8),
(24, '¿Cuál era la capital del Imperio Inca?', 'Cuzco', 'Quite', 'Machu picchu', 'Lima', 3, 8),
(25, '¿Cómo se llamaba el padre de Alejandro Magno?', 'Felipe ll de macedonia ', 'Ptolomeo l', 'Leonidas', 'Flipo l de grecia ', 4, 8),
(26, '¿Quién fue el primer emperador romano?', 'Cesar Augusto ', 'Julio cesar ', 'Nerón ', 'Caligula', 5, 8);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_question` (`id_question`);

--
-- Indices de la tabla `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_category` (`id_category`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `game`
--
ALTER TABLE `game`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `game_ibfk_1` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`);

--
-- Filtros para la tabla `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
