package department_employee_unidirectional;

import static lombok.AccessLevel.PROTECTED;

import department_employee_unidirectional.MapStructMapper.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @RequiredArgsConstructor // do not generate required args constructor for @NonNull fields because @NonNull constraint
                            // is not guaranteed
@NoArgsConstructor(access = PROTECTED)
@Getter
//@Accessors(fluent = true)
@ToString
@EqualsAndHashCode
public class EmployeeDTO
{
	/** mutable, but not nullable */
	@NonNull @Setter private String name;

	/** mutable, but not nullable */
	@NonNull private DepartmentDTO department;

	/**
	 * constructor with parameters for each non-nullable field
	 * <p>common clients use this constructor
	 */
	public EmployeeDTO(@NonNull String name, @NonNull DepartmentDTO department)
	{
		this.name = name;
		this.department = department;
//		department.add(this);
	}

	/** let this be used by mapstruct, manually map each immutable (no setter) field */
	@Default // necessary, seems to make sure mapstruct does not use no-args-constructor
	public EmployeeDTO(@NonNull EmployeeEntity employee, @NonNull MapStructMapper.MapStructCycleTrackingContext context)
	{
		this(employee.getName(), MapStructMapper.INSTANCE.departmentEntityToDepartmentDTO(employee.getDepartment(), context));
		log.debug("context {}", context);
	}

	public DepartmentDTO department(@NonNull DepartmentDTO department)
	{
//		boolean removeSuccess = this.department.remove(this);
//		log.info("removed {} from {}: {}", this, this.department, removeSuccess);
//		if (removeSuccess)
//		{
			this.department = department;
//			boolean addSuccess = department.add(this);
//			log.info("added {} to {}: {}", this, department, addSuccess);
//		}
		return this.department;
	}
}