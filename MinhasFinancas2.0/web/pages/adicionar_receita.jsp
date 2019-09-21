<%@include file="../includes/sessaoPaginaInterna.jsp" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Minhas Finanças - Adicionar Receita</title>

    <!-- Bootstrap Core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">
    <!-- Morris Charts CSS -->
    <link href="../vendor/morrisjs/morris.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
		<!-- MUDAR A COR DO NAVBAR: inverse (em vez de default) etc. -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img src="../img/money-logo.png" width="50px" ></img></a>
                <a class="navbar-brand" href="#">Minhas Finanças</a>
        
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
               
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#"><% out.println(nomeDoCara); %>
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        
                        <li><a href="configuracoes.jsp"><i class="fa fa-gear fa-fw"></i> Configurações</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Sair</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search"> <!-- form de pesquisa (REMOVER)
                        <!--    <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group-->
                        </li>
                        <!--<li>
                            <br><br>
                        </li>-->
                        <li>
                            <a href="painel.jsp"><i class="fa fa-dashboard fa-fw"></i> Painel</a>
                        </li>
						
						<!--  Receitas ---------------------------------------------------------------------------------------------------------->
						<li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-money"></i> Receitas<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="adicionar_receita.jsp"><i><span class="glyphicon glyphicon-plus-sign"></span></i> Adicionar</a>
                                </li>
                                <li>
                                    <a href="visualizar_receita.jsp"><i><span class="glyphicon glyphicon-list"></span></i> Visualizar</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
						
                        <!--  Despesas ---------------------------------------------------------------------------------------------------------->
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-alert"></i> Despesas<span class="fa arrow"></span></a> 
                            <ul class="nav nav-second-level">
                               <li>
                                    <a href="adicionar_despesa.jsp"><i><span class="glyphicon glyphicon-plus-sign"></span></i> Adicionar</a>
                                </li>
                                <li>
                                    <a href="visualizar_despesa.jsp"><i><span class="glyphicon glyphicon-list"></span></i> Visualizar</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
						
                            <!--  Categorias de Despesa ---------------------------------------------------------------------------------------------------------->
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-th  "></i> Categorias de Despesa<span class="fa arrow"></span></a> 
                            <ul class="nav nav-second-level">
                                <li>
                                    <!-- Esse ícone é do próprio bootstrap -->
                                    <a href="adicionar_categoria_despesa.jsp"><i><span class="glyphicon glyphicon-plus-sign"></span></i> Adicionar</a>
                                </li>
                                <li>
                                    <a href="visualizar_categoria_despesa.jsp"><i><span class="glyphicon glyphicon-list"></span></i> Visualizar</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
						<li>
						<!-- Formas de Pagamento ------------------------------------------------------------------------------------------->
                            <a href="#"><i class="fa fa-bar-chart-o fa-credit-card"></i> Formas de Pagamento<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                               <li>
                                    <a href="adicionar_forma_pagamento.jsp"><i><span class="glyphicon glyphicon-plus-sign"></span></i> Adicionar</a>
                                </li>
                                <li>
                                    <a href="visualizar_forma_pagamento.jsp"><i><span class="glyphicon glyphicon-list"></span></i> Visualizar</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
						
						
                        <!--  Metas ---------------------------------------------------------------------------------------------------------->
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-screenshot"></i> Metas<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                               <li>
                                    <a href="adicionar_meta.jsp"><i><span class="glyphicon glyphicon-plus-sign"></span></i> Adicionar</a>
                                </li>
                                <li>
                                    <a href="acompanhar_meta.jsp"><i><span class="glyphicon glyphicon-list"></span></i> Acompanhar Metas</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
						
                        <!--  Gráficos ---------------------------------------------------------------------------------------------------------->
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Gráficos<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                           
                                <li>
                                    <a href="grafico_receita.jsp">Receita</a>
                                </li>
                                <li>
                                    <a href="grafico_despesa.jsp">Despesa</a>
                                </li>
                                <li>
                                    <a href="grafico_balanco.jsp">Balanço</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li> 
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            <i><span class="glyphicon glyphicon-plus-sign"></span></i>
                            Adicionar Receita
                        </h1>
                        <!-- Breadcrumb -->
                        <ol class="breadcrumb">
                                <li><a href="#">Receitas</a></li>
                                <li class="active">Adicionar</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
				<div class="row">
                                <div class="col-lg-6">
                                    <form role="form" method="POST" action="..\AdicionarReceitaServlet">
                                        <div class="form-group">
                                            <label>Data</label>
											<!-- Pegar a data do dia atual automaticamente. O campo só existe caso o usuário faça uma receita retroativa-->
                                            <input type="date" id="dataReceita" name="dataReceita" value="2016-10-06" class="form-control">
                                            <p class="help-block">Qual a data que você obteve essa receita?</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Descrição</label>
                                            <input type="text" id="descricaoReceita" name="descricaoReceita" class="form-control">
                                            <p class="help-block">Lanches no carrinho de sorvete</p>
                                        </div>
                                        <!-- Falta opção para escolher a moeda da compra (não a padrão)-->
                                        <div class="form-group">
                                            <label>Preço</label>
                                            <div class="form-group input-group">
                                                <span class="input-group-addon">$</span>
                                                <input type="number" placeholder="100,00" id="preco" name="preco" class="form-control"></input>
                                            </div>											
                                        </div>
                                        <div class="form-group">
                                            <label>Moeda</label>
                                            <select class="form-control" id="moedaReceita" name="moedaReceita">
                                                <option>Real</option>
                                                <option>Dólar</option>
                                                <option>Euro</option>
                                                <option>Iene</option>
                                                              
                                            </select>
                                        </div>

                                        <!--<div class="form-group">
                                            <label>Valor</label>
                                            <input class="form-control">
                                        </div>-->
                                        <div class="form-group">
                                            <label>Tipo</label>
                                            <label class="radio-inline">
                                                <input type="radio" name="tipo" id="optionsRadiosInline1" value="Fixa" checked>Fixa
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="tipo" id="optionsRadiosInline2" value="Variavel">Variável
                                            </label>
                                        </div>
										<!--<div class="form-group">
                                            <label>Intervalo</label>
                                            <input type="number" min="1" class="form-control">
                                        </div>-->
                                        <div class="form-group">
                                            <label>Intervalo</label>
                                            <select class="form-control" id="intervalo" name="intervalo">
                                                <option value="">Selecione o intervalo</option>
                                                <option>Diário</option>
                                                <option>Semanal</option>
                                                <option>Mensal</option>
                                                <option>Trimestral</option>
                                                <option>Semestral</option>
                                                <option>Anual</option>

                                            </select>
                                        </div>
                                        <button type="submit" class="btn btn-success">Salvar</button>
                                        <button type="reset" class="btn btn-default">Cancelar</button>
                                    </form>
                                </div>
				</div>
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>
