public class prepforexam {
    public void main(String[] args){
        System.out.println("Question 3");
        int a = calcArea(7,12); //1
        short c = 7; //2
        calcArea(c,15); //3
        int d = calcArea(57); // 4 not legal
        calcArea(2,3); //5
        long t = 42; //6
        int f = calcArea(t,17);// 7 not legal
        int g = calcArea();// 8 not legal
        calcArea();// 9 not legal
        byte h = calcArea(4,20);// 10 not legal
        int j = calcArea(2,3,5);// 11 not legal
    }
    int calcArea(int height, int width){
        return height*width;
    }
}
