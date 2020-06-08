package controller.mode;

import controller.ClockSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FunctionListTest {
    
    ClockSystem clockSystem = new ClockSystem();
    FunctionList functionList = new FunctionList();

    @BeforeEach
    public void 객체_초기화(){
        clockSystem = new ClockSystem();
        functionList = new FunctionList();
    }

    @Test
    public void FunctionList_값_바꾸기_테스트(){
        
        //FunctionList의 최좌측 값을 최우측 값과 변경
        functionList.updateItemPosition(0, -1);
        Assertions.assertArrayEquals(new int[]{5, 1, 2, 3, 4, 0}, functionList.getFunctions());
        
        //FunctionList의 두번째 값을 한 칸 아래로 변경
        functionList.updateItemPosition(1, 1);
        Assertions.assertArrayEquals(new int[]{5, 2, 1, 3, 4, 0}, functionList.getFunctions());
    }
}
