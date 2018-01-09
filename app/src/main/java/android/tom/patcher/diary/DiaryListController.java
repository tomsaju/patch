package android.tom.patcher.diary;

import android.content.Context;
import android.database.Cursor;
import android.tom.patcher.helper.DBHelper;

/**
 * Created by tom.saju on 1/9/2018.
 */

public class DiaryListController implements IDiaryListController {
    Context context;
    IDiaryListView view;

    public DiaryListController(Context context, IDiaryListView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public Cursor getAllDiaryCursor() {
        DBHelper dbHelper = new DBHelper(context,DBHelper.DB_NAME,null,DBHelper.DB_VERSION);
        return dbHelper.getAllDiaryCursor();
    }
}
