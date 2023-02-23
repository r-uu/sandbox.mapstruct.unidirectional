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
@ToString
@EqualsAndHashCode
public class DepartmentDTO
{
	@NonNull @Setter private String name;

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
	}
}