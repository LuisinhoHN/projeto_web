<%@include file="../includes/sessaoPaginaExterna.jsp" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Minhas Finanças - Cadastro</title>

    <!-- Bootstrap Core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
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

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Crie sua conta</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" method="POST" action="..\CadastroServlet"> <!-- Teste -->
					<fieldset>
                                            <div class="form-group">
                                            <label>Nome:</label>
                                                    <input class="form-control" name="nome" type="text" value=""
                                                    maxlength="30" required autofocus>
                                            </div>
                                            <div class="form-group">
                                            <label>E-mail:</label>
                                                    <input class="form-control" value="" name="email" type="email"
                                                    maxlength="30" >
                                            </div>
                                            <div class="form-group">
                                            <label>Senha:</label>
                                                    <input class="form-control" value="" name="senha" type="password"
                                                     maxlength="20" >
                                            </div>
                                            <div class="form-group">
                                            <label>Data de Nascimento:</label>
                                                    <input class="form-control" name="Data_nascimento" type="date" required>
                                            </div>
                                            <div class="form-group form-inline">
                                                <label>Sexo:</label>
                                                <label class="radio-inline">
                                                        <input type="radio" name="sexo" id="sexo" value="Masculino" checked>Masculino
                                                </label>
                                                <label class="radio-inline">
                                                        <input type="radio" name="sexo" id="sexo" value="Feminino" >Feminino
                                                </label>
                                            </div>
                                            <div class="form-group">
                                            <label>País:</label>
                                                    <input class="form-control" placeholder="País" name="pais" type="text"
                                                    maxlength="20" required>
                                            </div>
                                            <div class="form-group">
                                            <label>Estado:</label>
                                                    <input class="form-control" placeholder="Estado" name="estado" type="text"
                                                    maxlength="20" required>
                                            </div>
                                            <div class="form-group">
                                            <label>Cidade:</label>
                                                    <input class="form-control" placeholder="Cidade" name="cidade" type="text"
                                                    maxlength="40" required>
                                            </div>
                                            <div class="form-group">
                                                    <button type="submit" class="btn btn-lg btn-success btn-block"><strong>Cadastrar-se</strong></button>
                                            </div>
                                    </fieldset>
				</form>
                    </div>
                </div>
            </div>
        </div>
    </div>

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
