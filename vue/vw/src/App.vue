<template>
  	<div id="app" class="fillcontain">

		    <router-view></router-view>

  	<!--<span>这是从消费者获取的JSON数据</span><br>
    {{this.info}} -->
</div>
</template>

 <script>  
export default { 
    name: 'App',
//  data() {      
//				return {        
//						info:{}    
//				}    
//		}, 
   
// 	created() {
//      //在created函数中使用axios的get请求向后台获取用户信息数据
//      this.$ajax('/api/hello').then(res => {
//	          this.info = res.data;
//	          console.log(res.data);
//      }).catch(function (error) {
//        console.log(error);
//      });
//    }
}
</script>

<style>  
/*.info{    
		width:100%;    
		height: 100%;  
}*/
</style>


