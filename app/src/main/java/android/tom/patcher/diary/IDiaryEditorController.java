package android.tom.patcher.diary;

/**
 * Created by tom.saju on 1/9/2018.
 */

public interface IDiaryEditorController {

    Diary getDiaryForId(String id);
    void saveDiary(Diary diary,boolean newDiary);
}
