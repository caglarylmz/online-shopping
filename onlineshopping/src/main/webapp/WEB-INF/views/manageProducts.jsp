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
					method="POST"
					enctype="multipart/form-data">
						<!-- Enter Product Name -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="name">Product Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name" placeholder="Enter Product Name" class="form-control"/>
								<sf:errors path="name" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						<!-- Enter Brand Name -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="brand">Brand Name</label>
							
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand" placeholder="Enter Brand Name" class="form-control"/>
								<sf:errors path="brand" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						<!-- Enter Product Description -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="description">Product Description</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4" placeholder="Enter Product Description" class="form-control"/>
								<sf:errors path="description" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						<!-- Enter Unit Price -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="unitPrice">Unit Price</label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice" placeholder="&#8378" class="form-control"/>
								<sf:errors path="unitPrice" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						<!-- Enter Quantity -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="quantity">Quantity Available</label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity" placeholder="Enter Quantity Available" class="form-control"/>
								
							</div>
						</div>
						<!-- File Uploads -->
						<div class="form-group row">
							<label class="control-label col-md-4" for="file">Select an Image</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control"/>	
								<sf:errors path="file" cssClass="help-block" element="em"></sf:errors>							
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
								<!-- add category button-->	
								<c:if test="${product.id==0}"> <!-- eğer var olan bir product editleniyorsa bu buton görünmesin -->
									<div class="text-right">
										<br/>
										<button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-xs">Add Category</button>								
									</div>
								</c:if>																	
							</div>
						</div>

						<!-- Button -->				
						<div class="text-left">		
							<div class="form-group row" >						
								<div class="col-md-8" >
									<input type="submit" name="submit" id="submit" value="Submit" class="btn btn-primary"/>
									<!-- Hidden Fields for Prododucts-->
									<sf:hidden path="id"/>
									<sf:hidden path="code"/>
									<sf:hidden path="supplierId"/>
									<sf:hidden path="active"/>
									<sf:hidden path="views"/>
								</div>
							</div>	
						</div>					
					</sf:form>			
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-xs-12">
			<h3>Available Products</h3>
			<hr/>
		</div>
		<div class="col-xs-12">
			<div style="overflow:auto">
				<!-- Products Table for Admin -->
				<table id="adminProductsTable" class="table table-stripped table-bordered">
					<thead>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Brand</th>
							<th>Unit Price</th>
							<th>Quantity</th>							
							<th>Activate</th>
							<th>Edit</th>
						</tr>
					</thead>					
					<tfoot>
					<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Brand</th>
							<th>Unit Price</th>
							<th>Quantity</th>
							<th>Activate</th>
							<th>Edit</th>
						</tr>
					</tfoot>
				</table>
			</div>			
		</div>		
	</div>
	
	<div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">	
					<h4 class="modal-title"><label class="control-label col-md-16">Add New Category</label></h4>			
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>					
				</div>				
				<div class="modal-body">				
					<!-- Category Form with Spring Form / Categorey.java property'lerine göre adlandırılır.-->
					<sf:form id="categoryForm" modelAttribute="category" action="${contextRoot}/manage/category" method="POST" class="form-horizontal">
						<!-- category Name -->
						<div class="form-group">
							<label for="category_name" class="control-label col-md-4">Category Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="category_name" class="form-control"/>
							</div>
						</div>
						<!-- category dexcription -->
						<div class="form-group">
							<label for="category_description" class="control-label col-md-4">Description</label>
							<div class="col-md-8">
								<sf:textarea cols="" rows="5" path="description" id="category_description" class="form-control"/>
							</div>
						</div>
						<!-- category submit -->
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" value="Add Category" class="btn btn-primary"/>
							</div>
						</div>
					</sf:form>							
				</div>					
			</div>
		</div>
	</div>
</div>