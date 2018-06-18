<div class="container">	
	<div class="row">
		<!-- Would be to display sidebar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp" %>
		</div>
		<!-- to display the actual products -->
		<div class="col-md-9">		
			<!-- added breadcrumb component -->
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllProducts == true}">
					
						<script>
							window.categoryId = '';					
						</script>
					
					<!-- add breadcomponent -->
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>					
						</ol>
					</c:if>
		
					<c:if test="${userClickCategoryProducts==true}">
					
						<script>
							window.categoryId='${category.id}';					
						</script>
					
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>
						</ol>
					</c:if>
				</div>				
			</div>	
			<!--create listproducttable-->
			<div class="row">
			
				<div class="col-xs-12">
				
					<table id="productListTable" class="table table-striped table-bordered">
					
						<thead>
							<tr>
								<th></th><!-- Sonradan ajax codu ile eklenen "image" alanı için boş sütun oluşturuyoruz -->				
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Qty. Avaiable</th>
								<th></th><!-- Sonradan ajax codu ile eklenen "view / add cart" alanları için boş sütun oluşturuyoruz -->
							</tr>
						</thead>

						<tfoot>
							<tr>
								<th></th><!-- Sonradan ajax codu ile eklenen "image" alanı için boş sütun oluşturuyoruz -->	
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Qty. Avaiable</th>
								<th></th><!-- Sonradan ajax codu ile eklenen "view / add cart" alanları için boş sütun oluşturuyoruz -->
							</tr>
						</tfoot>					
					</table>
				
				</div>
			
			</div>
			
			
				
		</div>		
	</div>
</div>