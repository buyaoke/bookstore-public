<template>
<div>
  <div style="margin: 10px 0">

    <el-carousel height="450px">
      <el-carousel-item v-for="item in images" :key="item.src">
        <img :src="item" alt="" style="width: 100%;">
      </el-carousel-item>
    </el-carousel>
  </div>
  <div style="margin: 10px 0">

  </div>
</div>
</template>

<script>
export default {
  name: "Home",
  data(){
    return {
      images:[
          require('../../assets/index1.jpg'),
          require("../../assets/index2.jpg")
      ]
    }
  }
}
</script>

<style scoped>

</style>