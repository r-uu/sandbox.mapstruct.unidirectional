package department_employee_unidirectional;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.IdentityHashMap;
import java.util.Map;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.mapstruct.factory.Mappers;

import lombok.ToString;

@Mapper
public interface MapStructMapper
{
	MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

	DepartmentEntity departmentDTOtoDepartmentEntity(DepartmentDTO    department, MapStructCycleTrackingContext context);
	DepartmentDTO    departmentEntityToDepartmentDTO(DepartmentEntity department, MapStructCycleTrackingContext context);

	EmployeeEntity employeeDTOtoEmployeeEntity(EmployeeDTO    employee, MapStructCycleTrackingContext context);
	EmployeeDTO    employeeEntityToEmployeeDTO(EmployeeEntity employee, MapStructCycleTrackingContext context);

	@ToString class MapStructCycleTrackingContext
	{
		private Map<Object, Object> knownInstances = new IdentityHashMap<Object, Object>();

		@BeforeMapping
		public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType)
		{
			return (T) knownInstances.get( source );
		}

		@BeforeMapping
		public void storeMappedInstance(Object source, @MappingTarget Object target)
		{
			knownInstances.put( source, target );
		}
	}

	@Target({CONSTRUCTOR})
	@Retention(CLASS)
	@interface Default { }
}