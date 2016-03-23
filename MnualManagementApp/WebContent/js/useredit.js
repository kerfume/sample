		$("#department").change(function() {
			$("#group").empty();

			var department = $(this).val();
			if (department == "") {
				return;
			}

			$.post("groupjsonaction.action", {
				"department" : department
			}, function(groupList) {
				var optionItems = new Array();
				//optionItems.push(new Option("", ""));
				for (key in groupList) {
					optionItems.push(new Option(groupList[key], key));
				}
				$("#group").append(optionItems);
			});
		});
		
		function setDays(form){
			//セットされてる年を取得（うるう年用）
			var year = form.birthday_year.selectedIndex + 1950;
			
			var month = form.birthday_month.selectedIndex + 1;
			var day_sel = form.birthday_day;
			switch(month){
			case 2:
				if(year % 4 == 0){
					day_sel.length = 29;
					for(var i=27;i <= 28;i++){
						day_sel.options[i].value = i+1;
						day_sel.options[i].text = i+1;
					}
				}else{
					day_sel.length = 28;
				}
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				day_sel.length = 30;
				for(var i=27;i <= 29;i++){
					day_sel.options[i].value = i+1;
					day_sel.options[i].text = i+1;
				}
				break;
			default:
				day_sel.length = 31;
				for(var i=27;i <= 30;i++){
					day_sel.options[i].value = i+1;
					day_sel.options[i].text = i+1;
				}
			}
			/*day_sel.length++;
			day_sel.options[day_sel.length - 1].value = 100;
			day_sel.options[day_sel.length - 1].text = year;*/
		}
		
		//loadwindow表示
		function openLoad(form){
			if(form.image.value != ""){
				var l = ( screen.width-640 ) / 2;
				var t = ( screen.height-480 ) / 2;
				loadwin = window.open("../html/load.html", "loadwin", "width=500,height=400,top=" + t + ",left=" + l);
				loadwin.forcas();
			}
		}
		
		window.unload = function(){
			var loadwin;
			}
		
		window.onunload = function(){
			 if( loadwin != null ) {
			loadwin.close();
			 }
		}
		