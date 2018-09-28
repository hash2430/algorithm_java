package previousExam2019;

class Solution2 {
    public int[] solution(int N, int[] stages) {
        int[] answer = {};
        int[] challenger = new int[N];
        // challenger 구하기
        for (int i = 0; i < stages.length; i++) {
            for (int j = 0; j < challenger.length; j++) {
                if (stages[i] == j+1) {
                    challenger[j]++;
                }
            }
        }

        // arrival 구하기
        int[] arrival = new int[N];
        for (int i = 0; i < N; i++) {
            arrival[i] = challenger[i];
        }
        for (int i = 0; i <stages.length; i++) {
            if (stages[i] == N+1) {
                arrival[N-1]++;
            }
        }
        for (int i = N-2; i >= 0; i--) {
            arrival[i] += arrival[i+1] ;
        }

        // failRate 생성
        double[] failRate = new double[N];
        for (int i = 0; i < N; i++) {
            if(arrival[i] == 0) {
                failRate[i] = 0;
            } else {
                failRate[i] = (double)challenger[i] / (double)arrival[i];
            }
        }

        answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = i+1;
        }
        // fail Rate 내림차순 index으로 answer 생성
        // failRate이 같으면 index가 작은 것을 앞으로
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N - i; j++) {
                if (failRate[j-1]<failRate[j]){
                    int tmp = answer[j-1];
                    answer[j-1] = answer[j];
                    answer[j] = tmp;

                    double tmpd;
                    tmpd = failRate[j-1];
                    failRate[j-1] = failRate[j];
                    failRate[j] = tmpd;
                }
            }
        }

        for (int i = 0; i < N-1; i++) {
            if (failRate[i] == failRate[i+1]){
                if (answer[i] > answer[i+1]) {
                    int tmp = answer[i];
                    answer[i] = answer[i+1];
                    answer[i+1] = tmp;
                }
            }
        }
        return answer;
    }
}
