# colherada
Projeto de Práticas Profissionais 3 
------------------------------------------------------------------------------------ BD ------------------------------------------------------------------------------------





-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 21-Nov-2021 às 03:23
-- Versão do servidor: 10.1.38-MariaDB
-- versão do PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `colherada`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `avaliacao`
--

CREATE TABLE `avaliacao` (
  `id` int(11) NOT NULL,
  `usuario` int(11) NOT NULL,
  `receita` int(11) NOT NULL,
  `curtida` int(11) DEFAULT NULL,
  `comentario` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `avaliacao`
--

INSERT INTO `avaliacao` (`id`, `usuario`, `receita`, `curtida`, `comentario`) VALUES
(1, 1, 2, 1, 'Gostei muito da receita. Todos amaram'),
(2, 4, 3, 0, 'Gostei muito da receita!! Ficou MARAVILHOSO'),
(3, 4, 2, 1, 'Gostei muito da receita!'),
(4, 2, 2, 0, '6/10'),
(5, 2, 9, 1, 'Fiz para meus filhos e eles AMARAM! Dá para fazer umas mudançinhas e criar novos sabores rs rs'),
(6, 1, 9, 0, 'Fiz mas achei meio gorduroso.... diminui a quantidade de gordura e mudei alguns ingrediente e ficou bom!'),
(7, 2, 6, 1, 'MELHOR SOPA DO MUNDO! Ótima opção caso esteja com vontade de esquentar o coração nesse inverno'),
(8, 1, 6, 0, 'Não gostei muito'),
(17, 2, 8, 0, 'AMO FRANDO XADREZ!'),
(18, 5, 8, 0, 'MARAVILHOSOOOOOOOO'),
(19, 5, 8, 0, 'Não me canso NUNCA. Perfeitoo'),
(20, 1, 11, 0, 'sem erros'),
(21, 8, 1, 0, 'Melhor cura para o Verão!');

-- --------------------------------------------------------

--
-- Estrutura da tabela `filtro`
--

CREATE TABLE `filtro` (
  `id` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `filtro`
--

INSERT INTO `filtro` (`id`, `nome`) VALUES
(1, 'Carnes'),
(2, 'Bebidas'),
(3, 'Básicos'),
(4, 'Lanches'),
(5, 'Sobremesas'),
(6, 'Estrangeiras'),
(7, 'Saudável'),
(8, 'Veganos'),
(9, 'Sopas');

-- --------------------------------------------------------

--
-- Estrutura da tabela `receitafiltro`
--

CREATE TABLE `receitafiltro` (
  `id` int(11) NOT NULL,
  `receita` int(11) NOT NULL,
  `filtro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `receitafiltro`
--

INSERT INTO `receitafiltro` (`id`, `receita`, `filtro`) VALUES
(1, 1, 2),
(2, 2, 5),
(3, 2, 7),
(4, 3, 2),
(6, 5, 8),
(7, 5, 7),
(8, 1, 7),
(9, 3, 7),
(10, 3, 8),
(11, 1, 8),
(12, 4, 8),
(13, 4, 7),
(14, 6, 9),
(15, 7, 8),
(16, 7, 6),
(17, 8, 1),
(18, 8, 3),
(19, 9, 4),
(20, 10, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `receitas`
--

CREATE TABLE `receitas` (
  `id` int(11) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `ingredientes` varchar(600) NOT NULL,
  `modoPreparo` varchar(1000) NOT NULL,
  `calorias` int(11) NOT NULL,
  `foto` varchar(500) DEFAULT NULL,
  `criador` int(11) NOT NULL,
  `avaliacao` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `receitas`
--

INSERT INTO `receitas` (`id`, `nome`, `ingredientes`, `modoPreparo`, `calorias`, `foto`, `criador`, `avaliacao`) VALUES
(1, 'Suco limão', '3 limões\r\nÁgua\r\nAdoçante/açúcar a gosto', 'Esprema os limões\r\nColoque 1l de água\r\nColoque o 5g de açúcar/ adoçante', 10, 'https://t2.uc.ltmcdn.com/pt/images/4/5/7/como_fazer_suco_de_limao_sem_acucar_29754_600.jpg', 1, 0),
(2, 'Picolé Frutas FIT', 'Frutas de sua preferência\r\n700 ml de suco (eu usei suco natural de acerola e laranja)\r\nAçúcar a gosto ou adoçante\r\nPalitos', 'Coloque as frutas picadas em cubinhos na picoleteira.\r\nAdicione o suco, usando funil.\r\nLeve ao freezer e quando ganhar consistência coloque os palitos.\r\nDeixe congelar.', 50, 'https://receitasincriveis.com/wp-content/uploads/2018/09/receita-de-picole-caseiro-de-salada-de-frutas-375x313.jpg', 1, 9),
(3, 'Suco Laranja', '2 Laranjas\r\n1l Água\r\nAçúcar/Adoçante', 'Espremas as laranjas\r\nPeineire e junte com a água\r\nAdicione 3 colheres de açúcar ou 50 gotas de adoçante', 20, 'https://conteudo.imguol.com.br/c/entretenimento/08/2017/12/26/suco-suco-de-laranja-1514300613024_v2_450x450.jpg', 5, 10),
(4, 'Salada de grão de bico', '1 cebola\r\n1 colher de sopa de mostarda\r\n2 colheres de sopa de cheiro-verde\r\n4 colheres de sopa de azeite\r\n4 dentes de alho, 200 g de grão-de-bico\r\nSuco de 1 limão\r\nSal a gosto', 'Misture a cebola, o alho, o azeite, a mostarda e o suco do limão. \r\nCozinhe o grão-de-bico\r\nRetire a casca e reserve. \r\nColoque em uma assadeira, salpique o sal, coloque o molho e misture bem. Leve à geladeira por 30 minutos.\r\nSalpique o cheiro-verde na hora de servir.', 200, 'https://cdn.panelinha.com.br/receita/1600878228697-saladaitaliana.jpg', 2, 10),
(5, 'Salada de tomate cereja com queijo e maçã', '2 xícaras de tomate cereja cortados ao meio\r\n1 xícara de queijo minas frescal light cortado em cubos\r\n1 maçã pequena com casca picada em cubos\r\ncheiro-verde picado a gosto\r\nsuco de 1 limão\r\nazeite de oliva a gosto\r\nsal a gosto', 'Misturar os tomates e a maçã.\r\nColocar os temperos, o queijo frescal e o cheiro verde.\r\nMisturar delicadamente.', 70, 'https://www.receiteria.com.br/wp-content/uploads/receitas-de-salada-caprese.jpg', 4, 1),
(6, 'Sopa de cebola', '1 dente de alho\r\n1 tablete de caldo de carne\r\n1 colher de farinha de trigo\r\n1/2 lata de creme de leite sem soro\r\n2 batatas pré-cozidas picadas\r\n2 colheres de manteiga\r\n4 xícaras de água fervente\r\n400 g de cebola fatiada\r\nSal\r\nPimenta do reino a gosto', 'Refogar na manteiga o alho, a cebola e as batatas.\r\nDissolver em 2 colheres de água fria o caldo de carne, sal, pimenta e farinha de trigo.\r\nJuntar tudo mais a água fervente e cozinhar por 20 minutos.\r\nDesligue o fogo e acrescente o creme de leite.\r\nBata tudo no liquidificador e leve ao fogo.\r\nAntes de servir, polvilhe com salsa e queijo. Bom apetite.', 150, 'https://img.cybercook.com.br/imagens/receitas/808/caldo-de-cebola.jpg', 3, 0),
(7, 'Sushi de legumes', '2 xícaras de arroz branco\r\n4 xícaras de água\r\n5 colheres de vinagre de arroz\r\n1 colher de açúcar\r\n1 colher de chá de sal\r\n1 envelopinho de ajinomoto\r\n1 cenoura\r\n1 pedaço pequeno de salmão\r\n1 colher de coentro moido\r\n3 vagens\r\n2 ovos\r\n2 folhas de nori (algas)\r\n1 esteira para enrolar sushi', 'Coloque os legumes e os ovos para cozinhar.\r\nEnquanto isso lave o arroz várias vezes até a água sair limpinha coloque em uma panela funda para cozinhar em fogo alto com a água, quando levantar fervura abaixe o fogo, e mexa de vez em quando pra que o arroz possa soltar a liga característica do arroz japonês.\r\nQuando estiver cozido e sem água está pronto.\r\nEm um vasilha separadamente misture o vinagre de arroz, o açúcar, o sal e o ajinomoto. Despeje o arroz ainda quente em uma vasilha e misture o líquido, faça isso na frente de um ventilador para evaporar o vinagre de arroz.\r\nColoque o Nori na esteira soloque uma camada do arroz e no meio coloque os legumes, ovos picados e salmão com coentro. Enrole com a esteira sempre pressionando para que a alga sele bem.', 180, 'https://www.culinariapravaler.com.br/view/site/img/receita/2019/09/receita-de-sushi-de-legumes-facil-culinaria-pra-valer.png', 2, 1),
(8, 'Frango Xadrez', '2 colheres (sopa) de azeite de oliva\r\n2 cebolas médias cortadas em cubos\r\n2 dentes de alho esmagados\r\n500 g de filé de frango sem pele e cortado em cubos\r\nsal a gosto\r\n1 pimentão verde cortado em cubos\r\n1 pimentão vermelho cortado em cubos\r\n1 pimentão amarelo cortado em cubos\r\n1 xícara (chá) de cogumelos em conserva cortados ao meio\r\n1/4 xícara de molho shoyu\r\n1 colher (sopa) de maisena\r\n1/2 xícara (chá) de água\r\n2 colheres (sopa) de amendoim torrado', 'Em uma frigideira ou panela grande, misture a metade do azeite de oliva, a cebola, o alho e deixe fritar.\r\nRetire e coloque em um prato.\r\nNa mesma panela, coloque o sal, o restante do azeite e frite os pimentões e os cogumelos por 5 minutos.\r\nRetire e despeje em outro prato.\r\nAinda na mesma panela, coloque o frango e frite até dourar.\r\nColoque todos os ingredientes novamente na frigideira, misture bem com uma colher de pau e refogue por mais 2 minutos.\r\nEm uma xícara, misture o molho shoyu, a maisena e a água.\r\nMexa bem e junte a mistura de frango.\r\nCozinhe, mexendo constantemente, até formar um molho espesso.\r\nColoque em uma travessa, polvilhe com amendoim e sirva quente.', 170, 'https://img.itdg.com.br/images/recipes/000/139/046/70726/70726_original.jpg', 5, 1),
(9, 'Misto quente de forno', 'Margarina para untar\r\n12 fatias de pão de forma (sem a casca)\r\n1/2 lata de molho de tomate pronto (coloquei 1 sache de sazon sabor do nordeste)\r\n6 fatias de presunto (ou a gosto)\r\n4 colheres de sopa de requeijão\r\n12 fatias de mussarela (ou a gosto)\r\n1/2 caixa de creme de leite\r\n1 tomate grande cortado em rodelas\r\norégano a gosto', 'Unte um refratário com margarina.\r\nForre o fundo com 6 fatias de pão de forma.\r\nColocar metade do molho de tomate temperado, presunto, camada de requeijão, metade da mussarela, restante do pão de forma, molho de tomate, creme de leite, mussarela, tomate em rodelas, orégano.\r\nLeve o refratário ao forno até a mussarela derreter (fiz no micro-ondas)', 240, 'https://cdn.ocp.news/2020/03/misto-quente.jpg', 1, 0),
(10, 'Arroz branco', '1 xícara de arroz lavado\r\n2 xícaras de água fervente\r\n1 dente de alho amassado\r\n1/4 de cebola picada\r\nazeite o suficiente\r\nsal a gosto', 'Refogue o alho e a cebola no azeite.\r\nColoque o arroz e deixe fritar por cerca de 30 segundos.\r\nAdicione a água fervente e o sal.\r\nAbaixe o fogo e deixe cozinhar até a água quase secar\r\nTampe a panela e aguarde cerca de 20 minutos antes de servir.\r\nSe desejar fazer mais, é só seguir as proporções, principalmente da água.', 60, 'https://vivareceita-cdn.s3.amazonaws.com/uploads/2020/11/Aprenda-como-fazer-um-arroz-dos-deuses.-Fonte-Pinterest-500x500.jpg', 5, 1),
(11, 'Pipoca', 'Milho\nÓleo\nSal', 'Coloca a óleo na panela junto com on milho.\nEspere estourar tudo\nAdicione sal a gosto\nAproveite', 375, 'https://www.casalcozinha.com.br/wp-content/uploads/2020/09/pipoca-de-micro-ondas-sem-oleo-light-144cal-6-1300x825.jpg', 5, 0),
(12, 'Ovo frito', 'Ovo\nÓleo\nSal\n', 'Em uma panela coloque o óleo e espere esquentar.\nColoque o ovo e frite\nPronto! Adicione sal a gosto', 107, 'https://static.clubedaanamariabraga.com.br/wp-content/uploads/2020/07/como-fazer-o-ovo-frito-perfeito.jpg', 1, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `receitasalva`
--

CREATE TABLE `receitasalva` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `receitas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `receitasalva`
--

INSERT INTO `receitasalva` (`id`, `user`, `receitas`) VALUES
(1, 3, 2),
(2, 3, 1),
(3, 4, 1),
(4, 4, 2),
(5, 1, 2),
(6, 1, 7),
(7, 1, 10),
(8, 4, 8),
(9, 3, 6),
(10, 2, 3),
(12, 2, 1),
(14, 2, 2),
(15, 1, 4),
(17, 2, 8),
(18, 5, 8),
(22, 8, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `foto` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `nome`, `email`, `senha`, `foto`) VALUES
(1, 'Ana', 'ana@gmail.com', '123', ''),
(2, 'Aidan', 'joao@gmail.com', '321', 'https://64.media.tumblr.com/2986846c1b484dd01ea048efbada9707/1f2d6b92e2e47e18-da/s250x400/872b3fbc478ef1662721e1833f0f0eaba31248ee.jpg'),
(3, 'Isabela', 'isa@gmail.com', '321', 'https://i.pinimg.com/736x/00/79/ce/0079ce9477bd5d5a608895242029d202.jpg'),
(4, 'Giovana', 'Giovana@gmail.com', '54321', 'https://i.pinimg.com/736x/00/79/ce/0079ce9477bd5d5a608895242029d202.jpg'),
(5, 'Milena Shishito', 'milena@gmail.com', '54321', 'https://i.pinimg.com/736x/00/79/ce/0079ce9477bd5d5a608895242029d202.jpg'),
(6, 'Mary', 'MaryStuart@gmail.com', '54321', 'https://i.pinimg.com/736x/00/79/ce/0079ce9477bd5d5a608895242029d202.jpg'),
(7, 'Henry', 'HenryStuart@gmail.com', '54321', 'https://i.pinimg.com/736x/00/79/ce/0079ce9477bd5d5a608895242029d202.jpg'),
(8, 'Olaf Jr', 'olaf@gmail.com', 'olaf', 'https://uploads.metropoles.com/wp-content/uploads/2020/09/09181752/olaf-frozen-1-600x400.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `avaliacao`
--
ALTER TABLE `avaliacao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_userAvalicao` (`usuario`),
  ADD KEY `fk_receitaAvalicao` (`receita`);

--
-- Indexes for table `filtro`
--
ALTER TABLE `filtro`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `receitafiltro`
--
ALTER TABLE `receitafiltro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_receitaFiltro` (`receita`),
  ADD KEY `fk_filtroReceita` (`filtro`);

--
-- Indexes for table `receitas`
--
ALTER TABLE `receitas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_criadorReceita` (`criador`);

--
-- Indexes for table `receitasalva`
--
ALTER TABLE `receitasalva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_receitaSalva` (`receitas`),
  ADD KEY `fk_criadorReceitaSalva` (`user`) USING BTREE;

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `avaliacao`
--
ALTER TABLE `avaliacao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `filtro`
--
ALTER TABLE `filtro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `receitafiltro`
--
ALTER TABLE `receitafiltro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `receitas`
--
ALTER TABLE `receitas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `receitasalva`
--
ALTER TABLE `receitasalva`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `receitafiltro`
--
ALTER TABLE `receitafiltro`
  ADD CONSTRAINT `fk_filtroReceita` FOREIGN KEY (`filtro`) REFERENCES `filtro` (`id`),
  ADD CONSTRAINT `fk_receitaFiltro` FOREIGN KEY (`receita`) REFERENCES `receitas` (`id`);

--
-- Limitadores para a tabela `receitas`
--
ALTER TABLE `receitas`
  ADD CONSTRAINT `fk_criadorReceita` FOREIGN KEY (`criador`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

