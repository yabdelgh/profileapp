package  ma.zyn.app.dao.criteria.core.project;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ProjectStateCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String label;
    private String labelLike;
    private String style;
    private String styleLike;



    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getLabelLike(){
        return this.labelLike;
    }
    public void setLabelLike(String labelLike){
        this.labelLike = labelLike;
    }

    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }
    public String getStyleLike(){
        return this.styleLike;
    }
    public void setStyleLike(String styleLike){
        this.styleLike = styleLike;
    }


}
