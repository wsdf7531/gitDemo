package acm;

/**
 * @Author: Xusj
 * @Date: 2018/9/4
 * @Description:
 */


public class Search {
    /**循环的方式 二分查找 --暂不支持重复的元素
     * arr 传入的数组
     * x 要查找的值
     * */
    public int binarySearch(int[] arr,int x){
        //最小索引
        int low=0;
        //最大索引
        int high=arr.length-1;
        while(low<=high){
            //中间值
            int middle=(low+high)/2;
            //当数组中间位为x时  返回
            if (arr[middle]==x){
                return middle;
            }//当数组中间位 大于 x 时，将最大索引变为middle-1，意味着第二次取中间值 在前部分取
            else if (arr[middle]>x){
                high=middle-1;
            }else {
                low=middle+1;
            }
        }
        return -1;
    }
    //调用二分查询
    public static int binarySearch(){
        int[] arr={4,5,8,9,7,4,2,11,15};
        int x=7;
        int res=new Search().binarySearch(arr,x);
        return res;
    }
}
