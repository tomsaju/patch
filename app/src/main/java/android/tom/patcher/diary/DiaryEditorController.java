package android.tom.patcher.diary;

import android.content.Context;
import android.tom.patcher.helper.DBHelper;
import android.tom.patcher.notes.INoteEditorView;

/**
 * Created by tom.saju on 1/9/2018.
 */

public class DiaryEditorController implements IDiaryEditorController {
    Context context;
    IDiaryEditorView view;

    public DiaryEditorController(Context context, IDiaryEditorView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public Diary getDiaryForId(String id) {
        Diary diary ;
        DBHelper dbHelper = new DBHelper(context,DBHelper.DB_NAME,null,DBHelper.DB_VERSION);
        diary = dbHelper.getDiaryForId(Integer.parseInt(id));
        return diary;
    }

    @Override
    public void saveDiary(Diary diary, boolean newDiary) {
        DBHelper dbHelper = new DBHelper(context,DBHelper.DB_NAME,null,DBHelper.DB_VERSION);
        dbHelper.insertintoDiaryTable(diary,newDiary);
        ((IDiaryEditorView)view).onDiaryInsertSuccess();
    }
}
