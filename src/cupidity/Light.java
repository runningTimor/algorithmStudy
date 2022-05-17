package cupidity;

/**
 * 安装最少的路灯
 */
public class Light {

    public static int getMinLights(String[] road){
        int ans = 0;
        int index =0;
        while (index<road.length){
            if ("x".equals(road[index])){
                index++;
            }else {
                ans++;
                if (index+1==road.length){
                    break;
                }
                if ("x".equals(road[index+1])){
                    index+=2;
                }else {
                    index+=3;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] road = {"x",".",".","x",".",".",".","x"};
        System.out.println(getMinLights(road));

    }

}
