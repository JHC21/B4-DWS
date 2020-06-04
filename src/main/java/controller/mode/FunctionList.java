package controller.mode;

import java.util.ArrayList;

public class FunctionList {
    private int[] functions; //(0, 1, 2, 3, 4, 5)


    public void updateItemPosition(int curPosition, int movePosition){
        //현재 선택자로 선택된 아이템의 번호 (curPosition)와,
        //그걸 위아래(+1/-1)로 이동하는걸 나타내는 movePosition을 받아
        //functions를 변경하는 메소드
        int dest = curPosition + movePosition;
        int temp = 0;

        if(dest < 0) dest = 5;
        if(dest > 5) dest = 0;

        temp = this.functions[curPosition];
        this.functions[curPosition] = this.functions[dest];
        this.functions[dest] = temp;

        return;
    }
    
    public int[] getFunctions() {
        return this.functions;
    }
}
