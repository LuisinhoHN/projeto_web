<%@page import="MinhasFinancas.DaoUtil.DaoFactory"%>
<%@page import="MinhasFinancas.model.FormaPagamento"%>
<%@include file="../includes/sessaoPaginaInterna.jsp" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int idFormaPagamento = (int) session.getAttribute("idFormaPagamento");
    
    FormaPagamento formaPagamento = null;

    DaoFactory daoFactory = DaoFactory.getInstance();
    
    formaPagamento = daoFactory.criarFormaPagamentoDao().find(idFormaPagamento);
%>
<!DOCTYPE html>
<html lang="pt">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Minhas Finanças - Adicionar Forma de Pagamento</title>

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
                            <i><span class="glyphicon glyphicon-pencil"></span></i>
                            Editar Forma de Pagamento
                        </h1>
                        <!-- Breadcrumb -->
                        <ol class="breadcrumb">
                                <li><a href="#">Formas de Pagamento</a></li>
                                <li class="active">Editar</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
				<div class="row">
                                <div class="col-lg-6">
                                    <form role="form" action="..\AtualizarFormaPagamento" method="POST">
                                        <div class="form-group">
                                            <label>Nome</label>
                                            <input value="<%= formaPagamento.getNome() %>" class="form-control" name="nomeFormaPagamento">
                                            <p class="help-block">Um nome para sua forma de pagamento</p>
                                        </div>
                                                                                
                                        <div class="form-group">
                                            <label>Descrição</label>
                                            <input value="<%= formaPagamento.getDescricao() %>" class="form-control" name="descricaoFormaPagamento">
                                            <p class="help-block"></p>
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
