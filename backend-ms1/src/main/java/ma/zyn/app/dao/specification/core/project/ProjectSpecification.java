package  ma.zyn.app.dao.specification.core.project;

import ma.zyn.app.dao.criteria.core.project.ProjectCriteria;
import ma.zyn.app.bean.core.project.Project;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProjectSpecification extends  AbstractSpecification<ProjectCriteria, Project>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("startDate", criteria.getStartDate(), criteria.getStartDateFrom(), criteria.getStartDateTo());
        addPredicate("endDate", criteria.getEndDate(), criteria.getEndDateFrom(), criteria.getEndDateTo());
        addPredicate("status", criteria.getStatus(),criteria.getStatusLike());
        addPredicateFk("student","id", criteria.getStudent()==null?null:criteria.getStudent().getId());
        addPredicateFk("student","id", criteria.getStudents());
        addPredicateFk("student","email", criteria.getStudent()==null?null:criteria.getStudent().getEmail());
        addPredicateFk("projectState","id", criteria.getProjectState()==null?null:criteria.getProjectState().getId());
        addPredicateFk("projectState","id", criteria.getProjectStates());
        addPredicateFk("projectState","code", criteria.getProjectState()==null?null:criteria.getProjectState().getCode());
    }

    public ProjectSpecification(ProjectCriteria criteria) {
        super(criteria);
    }

    public ProjectSpecification(ProjectCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
