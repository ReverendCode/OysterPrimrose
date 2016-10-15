package com.dyel.oysterprimrose;


/**
 * Created by adamcarlton on 10/15/16.
 */

public class ExerciseObject {

    private String _exercise;
    private String _image;
    private String _description;
    private String _comments;
    private String _equipment;
    private String _musclegroup;

    public ExerciseObject(String exercise, String image, String description, String comments, String equipment, String musclegroup){
        this._exercise = exercise;
        this._image = image;
        this._description = description;
        this._comments = comments;
        this._equipment = equipment;
        this._musclegroup = musclegroup;
    }

    public String get_exercise() {
        return _exercise;
    }

    public void set_exercise(String _exercise) {
        this._exercise = _exercise;
    }

    public String get_image() {
        return _image;
    }

    public void set_image(String _image) {
        this._image = _image;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_comments() {
        return _comments;
    }

    public void set_comments(String _comments) {
        this._comments = _comments;
    }

    public String get_equipment() {
        return _equipment;
    }

    public void set_equipment(String _equipment) {
        this._equipment = _equipment;
    }

    public String get_musclegroup() {
        return _musclegroup;
    }

    public void set_musclegroup(String _musclegroup) {
        this._musclegroup = _musclegroup;
    }

}

