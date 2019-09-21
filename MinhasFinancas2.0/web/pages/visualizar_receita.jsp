<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="MinhasFinancas.DaoUtil.DaoFactory"%>
<%@page import="MinhasFinancas.model.Usuario"%>
<%@page import="MinhasFinancas.model.Receita" %>
<%@page import="java.util.List" %>

<%@include file="../includes/sessaoPaginaInterna.jsp" %> 
<%       
    List<Receita> listaReceitas = null;

    DaoFactory daoFactory = DaoFactory.getInstance();

    Usuario oCara = (Usuario) sessao.getAttribute("oCara");

    listaReceitas = daoFactory.criarReceitasDao().findReceitas(oCara);

    request.setAttribute("listaReceitas",listaReceitas);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Minhas Finanças - Visualizar Receitas</title>

        <!-- Bootstrap Core CSS -->
        <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- DataTables CSS -->
        <link href="../vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

        <!-- DataTables Responsive CSS -->
        <link href="../vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

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

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            <i><span class="glyphicon glyphicon-list"></span></i>
                            Visualizar Receita
                        </h1>
                        <ol class="breadcrumb">
                            <li><a href="#">Receitas</a></li>
                            <li class="active">Visualizar</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">

                            <div class="panel-body">
                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                    <button type="button"
                                            onclick="window.location.href = 'adicionar_receita.jsp'"
                                            class="btn btn-default btn-sm">
                                        <font color="blue"> <span class="glyphicon glyphicon-plus" aria-hidden="true"></font></span> Cadastrar Receita
                                    </button>
                                    <br/>
                                    <br/>
                                    <tr>
                                        <th>Data</th>
                                        <th>Descrição</th>
                                        <th>Valor</th>
                                        <th>Moeda</th>
                                        <th>Tipo</th>
                                        <th>Intervalo</th>
                                        <th>Ação</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listaReceitas}" var="receitas">
                                            <tr class="odd gradeX">
                                                <td> <c:out value="${receitas.dataReceita}"/></td>
                                                <td> <c:out value="${receitas.descricao}"/></td>
                                                <td> <c:out value="${receitas.valor}"/></td>
                                                <td> <c:out value="${receitas.moedaId.nome}"/></td>
                                                <td> <c:out value="${receitas.tipoReceitaId.nome}"/></td>
                                                <td> <c:out value="${receitas.intervalo}"/></td>
                                                <td align="center"><font color="blue">

                                                    <form action="../SalvaReceitaServlet" method="POST">
                                                        <input type="hidden" value="${receitas.idReceita}" name="idReceita">
                                                        <button  type="submit" class="btn btn-default btn-sm">
                                                            <a href="#"><font color="blue">
                                                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></font> Editar 
                                                            </a>
                                                        </button>
                                                    </form>

                                                    <form action="../ExcluirReceitaServlet" method="POST">
                                                        <input type="hidden" value="${receitas.idReceita}" name="idReceita">
                                                        <button type="submit" class="btn btn-default btn-sm">
                                                            <a href="#"><font color="blue"> 
                                                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></font> Excluir 
                                                            </a>
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!-- /.table-responsive -->
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->

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

        <!-- DataTables JavaScript -->
        <script src="../vendor/datatables/js/jquery.dataTables.min.js"></script>
        <script src="../vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
        <script src="../vendor/datatables-responsive/dataTables.responsive.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="../dist/js/sb-admin-2.js"></script>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
                                                $(document).ready(function () {
                                                    $('#dataTables-example').DataTable({
                                                        responsive: true
                                                    });
                                                });
        </script>

    </body>

</html>