package department_employee_unidirectional;

import static lombok.AccessLevel.PROTECTED;

import department_employee_unidirectional.MapStructMapper.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
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

	/** let this be used by mapstruct, manually map each immutable (no setter) field */
	@Default // necessary, seems to make sure mapstruct does not use no-args-constructor
	public EmployeeDTO(@NonNull EmployeeEntity employee, @NonNull MapStructMapper.MapStructCycleTrackingContext context)
	{
		this(employee.getName(), MapStructMapper.INSTANCE.departmentEntityToDepartmentDTO(employee.getDepartment(), context));
		log.debug("context {}", context);
	}

	public DepartmentDTO department(@NonNull DepartmentDTO department)
	{
		this.department = department;
		return this.department;
	}
}