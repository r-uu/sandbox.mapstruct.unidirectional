package department_employee_unidirectional;

import static lombok.AccessLevel.PROTECTED;

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
@Getter()
//@Accessors(fluent = true)
@ToString
@EqualsAndHashCode
public class EmployeeEntity
{
	/** mutable, but not nullable */
	@NonNull @Setter private String name;

	/** mutable, but not nullable */
	@NonNull @Setter private DepartmentEntity department;

	/** let this be used by mapstruct, manually map each immutable (no setter) field */
	@MapStructMapper.Default
	public EmployeeEntity(@NonNull EmployeeDTO employee, @NonNull MapStructMapper.MapStructCycleTrackingContext context)
	{
		this(employee.getName(), new DepartmentEntity(employee.getDepartment(), context));
		log.debug("context {}", context);
	}
}