<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<!-- Left side column. contains the logo and sidebar -->
		<%@ include file="/WEB-INF/views/common/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Utilisateurs</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<!-- Horizontal Form -->
						<div class="box">
							<!-- form start -->
							<form class="form-horizontal" method="post"
								action="${pageContext.request.contextPath}/users/create">
								<div class="box-body">
									<div class="form-group">
										<label for="lastname" class="col-sm-2 control-label">Nom</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="lastname" name="lastname" placeholder="Nom" minLength="3" required>
										</div>
									</div>
									<div class="form-group">
										<label for="firstname" class="col-sm-2 control-label">Prenom</label>

										<div class="col-sm-10">
											<input type="text" class="form-control" id="firstname" name="firstname" placeholder="Prenom" minLength="3" required>
										</div>
									</div>
									<div class="form-group">
										<label for="email" class="col-sm-2 control-label">Email</label>

										<div class="col-sm-10">
											<input type="email" class="form-control" id="email"	name="email" placeholder="Email" required>
										</div>
									</div>

									<div class="form-group">
										<label for="birthdate" class="col-sm-2 control-label">Date de naissance</label>

										<div class="col-sm-10">
											<input type="date" class="form-control" id="birthdate"
												name="birthdate" onchange="checkAge()" required>
										</div>
									</div>
									<div class="alert alert-danger" role="alert"
										id="warningAgeSection" style="display: none">Il faut avoir 18 ans minimum !</div>
									<div class="alert alert-danger" role="alert"
										id="warningEmailSection" style="display: none">Cet email est déjà utilisé !</div>
								</div>
								<!-- /.box-body -->
								<div class="box-footer">
									<button type="submit" class="btn btn-info pull-right"
										id="btn_add">Ajouter</button>
								</div>
								<!-- /.box-footer -->
							</form>
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>
			</section>
			<!-- /.content -->
		</div>

		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/views/common/js_imports.jsp"%>

	<script>
		function checkAge() {

			var Bdate = document.getElementById('birthdate').value;
			var Bday = +new Date(Bdate);

			if (((Date.now() - Bday) / (31557600000)) > 18) {
				document.getElementById('btn_add').disabled = false;
				document.getElementById('warningAgeSection').style.display = 'none';
			} else {
				document.getElementById('btn_add').disabled = true;
				document.getElementById('warningAgeSection').style.display = 'block';
			}
		}

		const clientsMailsList = [
			<c:forEach var="user" items="${users}">
                '${user.email}',                  
            </c:forEach>                   
            ];
		
			$('#email').on('change',()=>{
			let result = clientsMailsList.find((element)=> element==$('#email').val());
			
			if(result===undefined){
				document.getElementById('btn_add').disabled = false;
				document.getElementById('warningEmailSection').style.display = 'none';
				}
			else {
				document.getElementById('btn_add').disabled = true;
				document.getElementById('warningEmailSection').style.display = 'block';
			}
			});
		
	</script>

</body>
</html>
