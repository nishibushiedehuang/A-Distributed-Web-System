<template>
	<div id="app">
		<div class = "title">
			<el-row :gutter="20">
				<el-col>
					<div>
						<el-menu :default-active="activeIndex1" 
							class="el-demo1" 
							mode="horizontal" 
							@select="handleSelect" 
							text-color="#000" 
							active-text-color="#eb002a" 
							router 
							style="width: 500px;margin-left:auto; margin-right:auto;">
								<el-menu-item index="/" style="font-size: 18px;">首页</el-menu-item>
								<el-menu-item index="film" style="font-size: 18px;">影片</el-menu-item>
								<el-menu-item index="theater" style="font-size: 18px;">影院</el-menu-item>
						</el-menu>
					</div>
				</el-col>
			</el-row>			
		</div>
		<div>
			<el-carousel indicator-position="outside" type="card"  height="400px">
			    <el-carousel-item v-for="(item, index) in carouselimg" :key="index">
			       <el-row>
			       	<el-col>
			       		<img ref = "imgHeight":src="item.idView" class = "banner_img" style="width: 100%;height: 100%;"/>
			       	</el-col>
			       	</el-row>
			    </el-carousel-item>
			</el-carousel>
		</div>
		<div>
			<el-container style="width: 1200px;margin-left:auto; margin-right:auto;">
				<el-menu :default-active="activeIndex2" class="el-demo2" mode="horizontal" @select="handleSelect" text-color="#000" active-text-color="#eb002a" 
					style="width: 200px;margin-left:0px; margin-right:auto;margin-bottom: 20px;">
						<el-menu-item index="1">正在热映</el-menu-item>
						<el-menu-item index="2">即将上映</el-menu-item>
				</el-menu>			
			</el-container>
		</div>
		<div>
			<el-container style="width: 1200px;margin-left:auto; margin-right:auto; border: 1px solid #eee">
	  			<el-row  style="border: 1px solid #eee"
				    <el-col :span="7" v-for="(o, index) in cardlist" :key="index" :offset="index > 0 ? 1 : 0" style="margin: 12px;">
					    <el-card :body-style="{ padding: '0px' }">
					        <img :src='o.idView'  class="image">
						        <div style="padding: 10px;">
						        <span>{{o.text}}</span><br>
						        <span>{{o.time}}</span>				       
					        </div>
					    </el-card>
				    </el-col>
				</el-row>	
		    </el-container>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
	        return {
	        	cardlist:[],
	        	carouselimg:[
	        		{id: 0, idView: require('../assets/images/0.jpg')},
	        	    {id: 1, idView: require('../assets/images/1.jpg')},
	        	    {id: 2, idView: require('../assets/images/2.jpg')},
	        	    {id: 3, idView: require('../assets/images/3.jpg')},
	        	    {id: 4, idView: require('../assets/images/4.jpg')},
	        	    {id: 5, idView: require('../assets/images/5.jpg')},
	        	    {id: 6, idView: require('../assets/images/6.jpg')}
	        	    
	        	],
				cardimg1:[
				    {id: 0, idView: require('../assets/images/7.jpg'),text:"叶问",time:"上映时间：2019-12-20"},
	        	    {id: 1, idView: require('../assets/images/8.jpg'),text:"误杀",time:"上映时间：2019-12-13"},
	        	    {id: 2, idView: require('../assets/images/9.jpg'),text:"灭绝",time:"上映时间：2020-01-18"},
	        	    {id: 3, idView: require('../assets/images/10.jpg'),text:"紫罗兰永恒花园",time:"上映时间：2020-01-10"},
	        	    {id: 4, idView: require('../assets/images/11.jpg'),text:"鲨海逃生",time:"上映时间：2020-01-10"},
				],
				cardimg2:[
				    {id: 0, idView: require('../assets/images/12.jpg'),text:"月半爱丽丝",time:"上映时间：2020"},
	        	    {id: 1, idView: require('../assets/images/13.jpg'),text:"五彩缤纷",time:"上映时间：2020"},
	        	    {id: 2, idView: require('../assets/images/14.jpg'),text:"通往春天的列车",time:"上映时间：2020-03-20"},
	        	    {id: 3, idView: require('../assets/images/15.jpg'),text:"撼山瑶",time:"上映时间：2020-03-20"},
	        	    {id: 4, idView: require('../assets/images/16.jpg'),text:"不期而遇",time:"上映时间：2020-03-27"},
				],
		       	activeIndex1: '/',
		       	activeIndex2:'1',
	        	radio:'1',
	        }
	    },
	  
	    methods: {
	        handleSelect(key, keyPath) {
	        	console.log(key, keyPath);
	        	if(key==1){
	        		this.cardlist = this.cardimg1;
	        		console.log("this.cardlist",this.cardlist);
	        	}else{
	        		this.cardlist = this.cardimg2;
	        	}
	        },
	        
	         
    	},
    	
    	mounted() {
	        this.cardlist = this.cardimg1;
	       
        }
	}	
</script>

<style>
#app{
    top: 0px;
    background-color: #ffffff;
    height: 100%;
}
 .el-carousel__item h3 {
    color: #475669;
    opacity: 0.5;
    line-height: 200px;
    margin: 100px;
    display: flex;
	align-items: center;
	justify-content: center;
  }

 .el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }
  
 .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
  }
.title{
	margin: 15px;
}
.banner_img{
	height: 100%;
}
 .bottom {
    margin-top: 13px;
    line-height: 12px;
  }
.image {
    width: 100%;
    display: block;
}

.clearfix:before,
.clearfix:after {
      display: table;
      content: "";
      clear: both
}
 </style>