<div class="container">

	<!-- Bradcrumb -->
	<div class="row">

		<div class="col xs-12">
			<!-- Home/Products/Product -->
			<ol class="breadcrumb">
				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/show/all/products">Products</a></li>
				<li class="active">${product.name}</li>
			</ol>
		</div>
	</div>

	<div class="row">
		<!--display the produt image-->
		<div class="col-xs-12 col-sm-4">
			<div class="thumbnail">
				<img src="${images}/${product.code}.jpg" class="img img-responsive" />
			</div>
		</div>
		<!--display the produt description-->
		<div class="col-xs-12 col-sm-4">
			<h3>${product.name}</h3>
			<hr />
			<p>${product.description}</p>
			<hr />
			<h4>
				Price: <strong> &#8378; ${product.unitPrice} /- </strong>
			</h4>
			<hr />
					
			<!-- solve zero products -->
			<c:choose>
				<c:when test="${product.quantity < 1}">				
					<h6>Quantity Avaible: <span style="color:red"><b>Out of Stock</b></span></h6>
					<a href="javascript:void(0)"
					class="btn btn-success disabled">
					<strike>
					<span class="glyphicon glyphicon-shopping-cart"></span>
					Add to Cart</strike>
					</a> 							
				</c:when>				
				<c:otherwise>
					<h6>Quantity Avaible: ${product.quantity}</h6>
					<a href="${contextRoot}/cart/add/${product.id}/product"
					class="btn btn-success">
					<span class="glyphicon glyphicon-shopping-cart"></span>
					Add to Cart</a> 
				</c:otherwise>
			</c:choose>
			
	
			<a href="${contextRoot}/show/all/products" 
				class="btn btn-primary">
				<span class="glyphicon glyphicon-chevron-left"></span>
				Back Products</a>

			
		</div>
	</div>
</div>