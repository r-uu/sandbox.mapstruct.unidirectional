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
@NoArgsConstructor(access = PROTECTED) // generate no args constructor for jsonb, jaxb, mapstruct, ...
@Getter
//@Accessors(fluent = true)
@ToString
@EqualsAndHashCode
public class DepartmentDTO
{
	@NonNull @Setter private String name;

	/** may be null to indicate that employees are not yet loaded */
//	@EqualsAndHashCode.Exclude
//	private Set<EmployeeDTO> employees;

	/**
	 * let this be used by mapstruct (@Default, @ObjectFactory) and make sure to manually call required args constructor
	 * @param department incoming entity to be used for construction of instance
	 * @param context incoming context to properly handling cyclic dependencies
	 */
	@Default // necessary, seems to make sure mapstruct does not use no-args-constructor
	public DepartmentDTO(@NonNull DepartmentEntity department, @NonNull MapStructMapper.MapStructCycleTrackingContext context)
	{
		this(department.getName());
		log.debug("context {}", context);

//		if (isNull(department.employees()) == false)
//		{
//			// TODO no, we should use a mapstruct mapper for that
//			department.employees().forEach(e -> add(new EmployeeDTO(e, context)));
//		}
	}

//	/** return unmodifiable */
//	public Set<EmployeeDTO> employees()
//	{
//		if (isNull(employees)) return null;
//		return Set.copyOf(employees);
//	}
//
//	public boolean add(@NonNull EmployeeDTO employee)
//	{
//		if (employee.getDepartment() == this)
//		{
//			if (employeesContains(employee)) return true;
//			return nonNullEmployees().add(employee);
//		}
//		else
//		{
//			// following check should never return true
//			if (employeesContains(employee))
//				log.error("employee with {} is already contained in {}", employee.getDepartment(), this);
//
//			// assign this department as department of employee and update employees
//			employee.department(this);
//			return nonNullEmployees().add(employee);
//		}
//	}
//
//	public boolean remove(@NonNull EmployeeDTO employee)
//	{
//		if (isNull(employees)) return false;
//		return employees.remove(employee);
//	}
//
//	private Set<EmployeeDTO> nonNullEmployees()
//	{
//		if (isNull(employees)) return new HashSet<>();
//		return employees;
//	}
//
//	private boolean employeesContains(EmployeeDTO employee)
//	{
//		if (isNull(employees)) return false;
//		return employees.contains(employee);
//	}
}