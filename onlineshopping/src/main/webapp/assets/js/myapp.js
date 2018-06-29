$(function() {
	//active element
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
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
					return '&#8378;' + data;//tl:&#8378
					}	
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
	//dismiising the alert after 3 seconds
	var $alert = $('.alert');
	if($alert.length){
		setTimeout(function(){
			$alert.fadeOut('slow');
			}, 3000);
	}	
	
	//data table for admin
	// code for jquery datatable
	// create dataset

	var $adminTable = $('#adminProductsTable');

	if($adminTable.length){
		//console.log('table mesagge');
		
		var jsonUrl= window.contextRoot + '/json/data/admin/all/products';
		
		
		$adminTable.DataTable({
			//Gösterilecek item sayısı
			//Eğer yazılmazsa 10,50,100 ve default 10 seçili gelir.
			lengthMenu : [[10,30,50-1],['10','30','50','ALL']],
			pageLength : 30,//default değeri
			
			ajax:{
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data:'id'
				},
				{					
					data: 'code',
					bSortable: false,// bu satır ile bu sütunda sıralama özelliği kapatılır.
					mRender: function(data,type,row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg"/>';
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
					return '&#8378;' + data;//tl:&#8378
					}	
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
					data : 'active',
					bSortable:false,
					mRender: function(data,type,row){
						var str = '';
						str += '<label class="switch">';
						if(data){
							str += '<input type="checkbox" checked="checked" value="'+row.id+'"/>';
						}else{
							str += '<input type="checkbox"  value="'+row.id+'"/>';
						}						
						str += '<span class="slider round"></span></label>';
						return str;						
					}
				},
				{
					data:'id',
					bSortable: false,
					mRender: function(data,type,row){
						var str='';
						str += '<a href="${contextRoot}/manage/'+data+'/product" class="btn btn-warning">';
						str += '<span class="glyphicon glyphicon-pencil"></span></a>';
						return str;
					}
				}					
			],
			//switch elemntine ulaşım sağlanıyor.
			initComplete: function(){
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on('change',function(){
					
					var checkbox = $(this);	//checkbox değişkeni html'deki checkbox elemtine eşitlendi
					var checked = checkbox.prop('checked');	//checked değişkeni checkbox'un checked'ına eşitlendi
					var dMsg = (checked)? 'You want to activate the product?': 'You want to deactivate the product';	//checked değişkenin durmuna göre dMsg değişkeni atadık
					var value = checkbox.prop('value');	// value değişkenine checbox'ın value'sunu atadık
					//user confirmation dialog
					bootbox.confirm({						
						message: dMsg,
						callback: function(confirmed){
							
							if(confirmed){//user press ok
								console.log(value);
								bootbox.alert({
									message: 'You are going to perform operation on product' + value 
								});
							}else{//user press cancel
								checkbox.prop('checked',!checked);
							}
						}				
					});		
				});	
				
			}
		});
	}
	
});