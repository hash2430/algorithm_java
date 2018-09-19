import java.util.Set;

public class bit_manipulation {

    // Q1) N의 i~j번째 bit에 M을 삽입
    //  bitwise operand로는 int를 쓴 다음에 나중에 확인차 바꿈
    //  Inetger.toBinaryString(new Integer(int))
    //  함수의 input으로 boolean[]가 들어왔을 때 int로 바꿔주는 api도 있나?
    //  => Integer.parseInt(String, 2)
    //  00111000의 마스크가 11000111보다 만들기 쉽다. 후자부터 만들려면 가장 큰 수를 일단 만들어야됨.
    public static int q01(int M, int N, int i, int j) {
        int mask1Int=0;
        for (int x = i; x < j; x++) {
            mask1Int = mask1Int |(1<< x);
        }
        mask1Int = ~mask1Int;

        int mask2Int = M<<i;
        return (N&mask1Int)|mask2Int;
    }

    // Q2) 못풀겠따.

    // Q3) 주어진 정수의 비트 하나만을 0에서 1로 바꿀 수 있을 때 1이 연속으로 나올 수 있는 가장 긴 길이
    // TODO: 힘들게 짜놓고 regex 때문에 틀릴뻔했다. *는 0 or many, +는 1 or many
    // TODO: String.replaceAll(a, b)과 String.replace(a, b)의 차이: 둘다 String의 모든 a를 b로 바꾼다.
    // TODO: replaceAll의 경우 a에 regex를 받는 것이 차이이다. replace도 사실상 모두 찾아바꾸기.
    // TODO:10 패턴에 대한 처리가 안되어있다.
    public static int q03(int input) {
        String pattern01 = "01";
        String bitArray = Integer.toBinaryString(input);
        int[] idxList = new int[bitArray.length()];
        int patternIdx = bitArray.indexOf(pattern01);
        int cnt;
        int idxCnt = 0;
        if (bitArray.indexOf('1') == -1) {
            return 0;
        } else if (bitArray.indexOf('0')==-1) {
            return bitArray.length();
        }

        // 리스트에 0 idx를 적고 더 찾아본다.
        cnt=countSequential1(bitArray);
        idxList[0] = patternIdx;
        idxCnt++;
        while (patternIdx !=-1&&patternIdx<bitArray.length()-1) {
            patternIdx = bitArray.indexOf(pattern01, patternIdx+1);
            if (patternIdx==-1) {
                break;
            }
            idxList[idxCnt] = patternIdx;
            idxCnt++;
        }

        // TODO: String.replace()가 replaceAll을 호출하는 것 같다.
        for (int i = 0; i < idxCnt;i++) {
            int newCnt = 0;
            char[] newString = bitArray.toCharArray();
            newString[idxList[i]] = '1';
            newCnt = countSequential1(new String(newString));
            if (newCnt > cnt) {
                cnt=newCnt;
            }
        }
        return cnt;
    }
    public static int countSequential1(String str) {
        String[] strArr = str.split("[0]+");
        int cnt=0;
        int tmp;
        int[] cntArr = new int[strArr.length];
        // The biggest length of strArr element
        for (int i=0; i < strArr.length;i++) {
            cntArr[i] = strArr[i].length();
        }
        for (int i=0; i < strArr.length;i++) {
            for(int j=1; j<strArr.length-i;j++){
                if (cntArr[j]<cntArr[j-1]){
                    tmp=cntArr[j];
                    cntArr[j]=cntArr[j-1];
                    cntArr[j-1]=tmp;
                }
            }
        }
        return cntArr[strArr.length-1];
    }
    public static void main(String[] args){
        // Q1
        int M = 10;
        int N = 3500;
        int i = 2;
        int j = 5;
        int q01 = q01(M, N, i, j);
        String q01M = Integer.toBinaryString(new Integer(M));
        String q01N = Integer.toBinaryString(new Integer(N));
        String q01Str = Integer.toBinaryString(new Integer(q01));
        System.out.println("Q1: ");
        System.out.println(q01M);
        System.out.println(q01N);
        System.out.println(q01Str);

        // Q3
        int q3Int = 1775;
        int q31s = q03(q3Int);
        System.out.println("Q3:" + q31s);
    }


}
