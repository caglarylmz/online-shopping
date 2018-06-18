$(function() {
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	default:
		$('#home').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// code for jquery datatable
	// create dataset

	var $table = $('#productListTable');

	if($table.length){
		//console.log('table mesagge');
		
		var jsonUrl='';
		if(window.categoryId == ''){
			//http://localhost:8080/onlineshopping/json/data/all/products
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}else{
			//http://localhost:8080/onlineshopping/json/data/category/{id}/products
			jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryId +'/products';
		}
		
		$table.DataTable({
			//Gösterilecek item sayısı
			//Eğer yazılmazsa 10,50,100 ve default 10 seçili gelir.
			lengthMenu : [[3,5,10,-1],['3','5','10','ALL']],
			pageLength : 5,//default değeri
			
			ajax:{
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					//prduct koduna sahip image dosyları olacak. Bu dosyalar //assets/images içinde tutulacak
					data: 'code',
					bSortable: false,// bu satır ile bu sütunda sıralama özelliği kapatılır.
					mRender: function(data,type,row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
					}
				},
				{
					data: 'name'					
				},
				{
					data: 'brand'					
				},
				{
					data: 'unitPrice',
					//ücretin önüne tl basar
					mRender: function(data,type,row){
					return '&#8378;' + data}	//tl:&#8378
				},
				{
					data: 'quantity',
					mRender: function(data,type,row){
						if(data<1){
							return '<span style="color:red">Out of Stock</span>';
						}
						return data;
					}
				},
				{
					//id'ye niteliğine sahip bir sütun için seçilen product'un ayrıntısını gösteren veya satınalınlar listesine ekleyen bir sütun yaratıyoruz
					data : 'id',
					bSortable: false,// bu satır ile bu sütunda sıralama özelliği kapatılır.
					mRender : function(data,type,row){
						var str='';
						str += '<a href="' + window.contextRoot + '/show/' + data + 
						'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>&#160;';
						
						if(row.quantity<1){
							str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}else{
							str += '<a href="' + window.contextRoot + '/cart/add/' + data + 
							'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
						
						return str;
					}
				}
			]
		});
	}
	
});