<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<div class="row">
	<!-- Alert Dialog -->
		<c:if test="${not empty message}">
			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>
		</c:if>
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Product Management</h4>
				</div>
				<div class="panel-body">
					<!-- Form Elements -->
					<sf:form class="form-vertical" modelAttribute="product"
					action="${contextRoot}/manage/products"
					method="POST">
						<!-- Enter Product Name -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="name">Product Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name" placeholder="Enter Product Name" class="form-control"/>
							</div>
						</div>
						<!-- Enter Brand Name -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="brand">Brand Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand" placeholder="Enter Brand Name" class="form-control"/>
							</div>
						</div>
						<!-- Enter Product Description -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="description">Product Description</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4" placeholder="Enter Product Description" class="form-control"/>
							</div>
						</div>
						<!-- Enter Unit Price -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="unitPrice">Unit Price</label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice" placeholder="&#8378" class="form-control"/>
							</div>
						</div>
						<!-- Enter Quantity -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="quantity">Quantity Available</label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity" placeholder="Enter Quantity Available" class="form-control"/>
							</div>
						</div>
						<!-- Enter Category Id  -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="categoryId">Select Category</label>
							<div class="col-md-8">
								<sf:select class="form-control" id="categoryId" path="categoryId"
								items="${categories}"
								itemLabel="name"
								itemValue="id"/>																		
							</div>
						</div>

						<!-- Button -->
						<div class="form-group row">						
							<div class="col-md-8">
								<input type="submit" name="submit" id="submit" value="Submit" class="btn btn-primary"/>
								<!-- Hidden Fields for Prododucts-->
								<sf:hidden path="id"/>
								<sf:hidden path="code"/>
								<sf:hidden path="supplierId"/>
								<sf:hidden path="active"/>
								<sf:hidden path="views"/>
							</div>
						</div>
					</sf:form>			
				</div>
			</div>
		</div>
	</div>
</div>