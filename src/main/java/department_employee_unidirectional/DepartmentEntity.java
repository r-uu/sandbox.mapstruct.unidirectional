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
@NoArgsConstructor(access = PROTECTED) // generate no args constructor for jpa, mapstruct, ...
@Getter()
//@Accessors(fluent = true)
@ToString
@EqualsAndHashCode
public class DepartmentEntity
{
	@NonNull @Setter private String name;

//	/** may be null to indicate that employees are not yet loaded */
//	@EqualsAndHashCode.Exclude
//	private Set<EmployeeEntity> employees;

	/**
	 * let this be used by mapstruct (@Default, @ObjectFactory) and make sure to manually call required args constructor
	 * @param department incoming DTO to be used for construction of instance
	 * @param context incoming context to properly handling cyclic dependencies
	 */
	@Default // necessary make sure mapstruct does not use no-args-constructor
	public DepartmentEntity(@NonNull DepartmentDTO department, @NonNull MapStructMapper.MapStructCycleTrackingContext context)
	{
		this(department.getName());
		log.debug("context {}", context);

//		if (isNull(department.employees()) == false)
//		{
//			// TODO no, we should use a mapstruct mapper for that
//			department.employees().forEach(e -> add(new EmployeeEntity(e, context)));
//		}
	}

//	/** return unmodifiable */
//	public Set<EmployeeEntity> employees()
//	{
//		if (isNull(employees)) return null;
//		return Set.copyOf(employees);
//	}
//
//	public boolean add(@NonNull EmployeeEntity employee)
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
//			employee.setDepartment(this);
//			return nonNullEmployees().add(employee);
//		}
//	}
//
//	public boolean remove(@NonNull EmployeeEntity employee)
//	{
//		if (isNull(employees)) return false;
//		return employees.remove(employee);
//	}
//
//	private Set<EmployeeEntity> nonNullEmployees()
//	{
//		if (isNull(employees)) return new HashSet<>();
//		return employees;
//	}
//
//	private boolean employeesContains(EmployeeEntity employee)
//	{
//		if (isNull(employees)) return false;
//		return employees.contains(employee);
//	}
}