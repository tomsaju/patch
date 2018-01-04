package android.tom.patcher.notes;

/**
 * Created by tom.saju on 12/15/2017.
 */

public interface INoteEditorController {

    Note getNoteForId(String id);
    void saveNote(Note note,boolean newNote);
}
