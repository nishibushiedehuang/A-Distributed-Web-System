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
			<el-container style="width: 1100px;margin-left:auto; margin-right:auto;margin-bottom:20px;margin-top: 20px;">
				<el-breadcrumb separator-class="el-icon-arrow-right"  style="font-size: 16px;">
				    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				    <el-breadcrumb-item :to="{ path: '/film' }">影片</el-breadcrumb-item>
				</el-breadcrumb>
			</el-container>
		</div>
		<div>
			<el-container style="width: 1200px;margin-left:auto; margin-right:auto;">
				<el-menu :default-active="activeIndex2" 
					class="el-demo2" mode="horizontal"
					@select="handleSelect" text-color="#000" 
					active-text-color="#eb002a" 
					style="width: 700px;margin-left:0px; margin-right:auto;margin-bottom: 20px;">
						<el-menu-item index="1">正在热映</el-menu-item>
						<el-menu-item index="2">即将上映</el-menu-item>
				</el-menu>
			</el-container>
		</div>
		<div >
			<el-container style="width: 1200px;margin-left:auto; margin-right:auto; border: 1px solid #eee">
	  			<el-row style="width: 700px;border-right: 1px solid #eee">
				    <el-col :span="7" 
				    	v-for="(o, index) in cardlist" 
				    	:key="index" :offset="(index%3)==0 ? 0 : 1" 
				    	style="margin: 14px;">
					    <el-card :body-style="{ padding: '0px' }">
					        <img :src='o.idView'  class="image">
						        <div style="padding: 10px;">
						        <span>{{o.text}}</span><br />
						        <span>{{o.time}}</span>				       
					        </div>
					    </el-card>
				    </el-col>
				</el-row>
				<el-row style="width: 500px">
					<el-col >
					 	<div>
					 		<div style="margin-left:150px;font-size: 18px;line-height: 1.7;padding-top: 20px;">
						 		<i class="el-icon-arrow-left"></i>
						 		奥斯卡精选
						 		<i class="el-icon-arrow-right"></i>
					 		</div>
					 		<div v-if="flag=='false'">
						 		<el-container style="width: 500px;height=700px; margin-left:auto; margin-right:auto" >
							 		<div>
								 		<el-row style="width: 220px">
										    <el-col :span="22" style="margin: 14px;">
											    <el-card :body-style="{ padding: '0px' }">
											        <img src="../assets/images/25.jpg" class="image">
											    </el-card>
										    </el-col>				  
										</el-row>
									</div>
									<div style="margin-top:15px;">
										<el-row style="width: 270px;">
											<el-col>
												<div style="text-align:left;font-size: 26px;margin-left:15px;margin-top:40px;line-height:0.5;" >
													寄生虫
												</div><br />
												<div style="text-align:left;font-size: 22px;margin-left:15px;line-height:0.5;">
													기생충(2019)
												</div>
											    <el-button 
											    	@click="more" 
											    	type="text" 
											    	class="button" 
											    	style="text-align:left;font-size: 16px;margin-left:15px;margin-top:15px;">
											    	韩国 /剧情 喜剧 /上映时间：2019<br/>年-05-21(戛纳电影节)/片长...>
											    </el-button>
											    <div style="margin-top: 30px;" >
												    <el-button style="text-align:left;font-size: 20px;margin:10px;" >
												    	<i class="el-icon-star-off" style="text-align:left;margin-right: 10px;"></i>想看
												    </el-button>
												    <el-button style="text-align:left;font-size: 20px;" >
												    	<i class="el-icon-circle-check" style="text-align:left;margin-right: 10px;"></i>看过
												    </el-button>
												</div>
											</el-col>
										</el-row>
									</div>
									
								</el-container>
								<el-container style="width: 480px;height=700px; margin-left:10px; margin-right:10px;border-radius: 20px;background-color:#00796b ;" >
									<div>
										<el-row style="width: 170px;">
											<el-col>
												<div style="text-align:left;font-size: 15px;margin-left:15px;margin-top:15px;color: #ffffff;">
													豆瓣评分™</div>
												<div style="text-align:left;font-size: 32px;margin-left:50px;color: #ffffff;">
													8.7</div>
												<div  style="text-align:left;font-size: 20px;margin-left:40px;margin-bottom:15px;">
													<el-rate
													  v-model="grade"
													  disabled
													  text-color="#ff9900" >
													</el-rate>
												</div>
											</el-col>
										</el-row>
									</div>
									<div>
										<el-row style="width: 280px;">
											<el-col style="width: 100px;margin-top:10px; margin-left:10px;">
												<div>
													<i class="el-icon-star-off" ></i>
													<i class="el-icon-star-off" ></i>
													<i class="el-icon-star-off" ></i>
													<i class="el-icon-star-off"></i>
													<i class="el-icon-star-off"></i>
												</div>
												<div style="margin-left:18px">
													<i class="el-icon-star-off"></i>
													<i class="el-icon-star-off"></i>
													<i class="el-icon-star-off"></i>
													<i class="el-icon-star-off"></i>
												</div>
												<div  style="margin-left:36px">
													<i class="el-icon-star-off"></i>
													<i class="el-icon-star-off"></i>
													<i class="el-icon-star-off"></i>
												</div>
												<div style="margin-left:54px">
													<i class="el-icon-star-off"></i>
													<i class="el-icon-star-off"></i>
												</div>
												<div style="margin-left:72px">
													<i class="el-icon-star-off"></i>
												</div>
											</el-col>
										
											<el-col style="width: 150px;margin-top:15px;">
												<el-progress  
													:stroke-width="8" 
													:percentage="percentage1" 
													:color="customColor" 
													:show-text="false" 
													style="width: 150px;margin-bottom: 14px;"></el-progress>
												<el-progress  
													:stroke-width="8" 
													:percentage="percentage2" 
													:color="customColor" 
													:show-text="false"  
													style="width: 150px;margin-bottom: 14px;"></el-progress>
												<el-progress  
													:stroke-width="8" 
													:percentage="percentage3" 
													:color="customColor" 
													:show-text="false"  
													style="width: 150px;margin-bottom: 14px;"></el-progress>
												<el-progress  
													:stroke-width="8" 
													:percentage="percentage4" 
													:color="customColor" 
													:show-text="false"  
													style="width: 150px;margin-bottom: 14px;"></el-progress>
												<el-progress  
													:stroke-width="8" 
													:percentage="percentage5" 
													:color="customColor" 
													:show-text="false"  
													style="width: 150px;"></el-progress>
												</el-col>
										</el-row>
										
									</div>
								</el-container>
								<el-container style="width: 500px;height=700px; margin-left:auto; margin-right:auto" >
									<div style="margin: 10px">
										<span>所属频道</span>
										<mu-chip class="demo-chip" style="margin-left: 10px" color="#00796b">
										    人性 <i class="el-icon-arrow-right" style="margin-left: 5px"></i>
										</mu-chip>
										<mu-chip class="demo-chip" style="margin-left: 10px" color="#00796b">
										    韩国 <i class="el-icon-arrow-right" style="margin-left: 5px"></i>
										</mu-chip>
										<mu-chip class="demo-chip" style="margin-left: 10px" color="#00796b">
										    社会 <i class="el-icon-arrow-right" style="margin-left: 5px"></i>
										</mu-chip>
										<mu-chip class="demo-chip" style="margin-left: 10px" color="#00796b">
										    黑色幽默 <i class="el-icon-arrow-right" style="margin-left: 5px"></i>
										</mu-chip>
									</div>
								</el-container>
								<el-container style="width: 500px;height=700px; margin-left:auto; margin-right:auto" >
									<div style="margin: 10px">
										<div style="text-align:left;font-size: 22px;">
											简介</div>
											&#8195;&#8195;基宇(崔宇植饰)出生在一个贫穷的家庭之中和妹妹基婷(朴素丹饰)以及父母在狭窄的地下室里过着相依为命的日子。
											一天，基宇的同学上拜访，他告诉基宇，自己在一个有钱人家里给他的女儿做家教，太太是一个头脑简单出手又阔绰女人，
											因为自己要出国留学，所以将家教的职位时转交给基宇。就这样，基宇来到了朴社长(李善均饰)家中，并且见到了他的
											太太(赵汝贞饰)，没过多久，基宇的妹妹和父母也如同寄生般的进入了朴社长家里工作。然而，他们的野心并没有止步
											于此，基宇更是和大小姐坠入了爱河。随着时间的推移，朴社长家里隐藏的秘密渐渐浮了水面。©豆瓣
									</div>
								</el-container>
							</div>
							<div v-else>
								<el-row style="width: 500px;border: 1px solid #eee">
									<el-col>
										<div style="text-align:left;font-size: 26px;margin-left:15px;margin-top:10px;margin-bottom:10px;" >影片信息</div>
										<span class="qes">片名:</span>
										<span class="aws">寄生虫</span><br />
										<span class="qes">原名:</span>
										<span class="aws">杏</span><br />
										<span class="qes">又名:</span>
										<span class="aws">寄生上流(台)/上流寄生族(港)/叫己oE</span><br />
										<span class="ln">/Parasite/Gisaengchung</span><br />
										<span class="qes">导演:</span>
										<span class="aws">奉俊昊</span><br />
										<span class="qes">编剧:</span>
										<span class="aws">奉俊昊/韩进元</span><br />
										<span class="qes">主演:</span>
										<span class="aws">宋康昊/李善均/赵汝贞/崔宇植/朴素丹/李静恩/张慧珍</span><br />
										<span class="ln">/郑知晓/朴明勋/朴叙俊/朴根祿/郑贤俊/朴孝新/安德</span><br />
										<span class="ln">烈亚斯·弗龙克</span><br />
										<span class="qes">上映:</span>
										<span class="aws">2019-05-21(戛纳电影节)/2019-05-30(韩国)</span><br />
										<span class="qes">类型:</span>
										<span class="aws">剧情/喜剧</span><br />
										<span class="qes">片长:</span>
										<span class="aws">132分钟</span><br />
										<span class="qes">地区:</span>
										<span class="aws">韩国</span><br />
										<span class="qes">语言:</span>
										<span class="aws">韩语/英语</span><br />
										<span class="qes">MDb:</span>
										<span class="aws">tt6751668</span><br />
										<el-button @click="back" 
											type="text" 
											class="button" 
											style="text-align:left;font-size: 15px;margin-left:15px;margin-top:15px;">
											<返回
										</el-button>
									</el-col>
								</el-row>
							</div>
					 	</div>				 	
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
	        	    {id: 5, idView: require('../assets/images/17.jpg'),text:"六人",time:"上映时间：2020-04-04"},
	        	    {id: 6, idView: require('../assets/images/18.jpg'),text:"奇妙王国之魔法奇缘",time:"上映时间：2020-04-11"},
	        	    {id: 7, idView: require('../assets/images/19.jpg'),text:"大红包",time:"上映时间：2020-04-30"},
	        	    {id: 8, idView: require('../assets/images/20.jpg'),text:"金蝉降魔",time:"上映时间：2020-05-08"},
	        	    {id: 9, idView: require('../assets/images/21.jpg'),text:"82号古宅",time:"上映时间：2020-05-15"},
				],
		       	activeIndex1: 'film',
		       	activeIndex2:'1',
		       	flag:'false',
		       	grade: 4.35,
		       	percentage1: 47,
		       	percentage2: 43.2,
		       	percentage3: 8.8,
		       	percentage4: 0.7,
		       	percentage5: 0.3,
		       	customColor: '#ff9900',
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
	        more(){
	        	this.flag = 'true';
	        	console.log("this.flag",this.flag);
	        },
	        back(){
	        	this.flag = 'false';
	        }
	         
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
}

.title{
	margin: 15px;
}
.image {
    width: 100%;
    display: block;
}
.el-icon-arrow-left .el-icon-arrow-right .Oscar{
	font-size: 18px;
	line-height: 1.5;
}
.qes{
	text-align:left;
	font-size: 16px;
	margin-left:15px;
	color:#909399;
}
.aws{
	text-align:left;
	font-size: 16px;
	margin-left:15px;
}
.ln{
	text-align:left;
	font-size: 16px;
	margin-left:70px;
}
</style>