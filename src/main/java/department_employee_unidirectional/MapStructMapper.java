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

//	@ObjectFactory default DepartmentDTO createDepartmentDTO(
//			@NonNull DepartmentEntity department, @NonNull @Context MapStructMapper.MapStructCycleTrackingContext context)
//	{
//		return new DepartmentDTO(department, context);
//	}
//
//	@ObjectFactory default DepartmentEntity createDepartmentEntity(
//			@NonNull DepartmentDTO department, @NonNull @Context MapStructMapper.MapStructCycleTrackingContext context)
//	{
//		return new DepartmentEntity(department, context);
//	}
//
//	@ObjectFactory default EmployeeDTO createEmployeeDTO(
//			@NonNull EmployeeEntity employee, @NonNull @Context MapStructMapper.MapStructCycleTrackingContext context)
//	{
//		return new EmployeeDTO(employee, context);
//	}
//
//	@ObjectFactory default EmployeeEntity createEmployeeEntity(
//			@NonNull EmployeeDTO employee, @NonNull @Context MapStructMapper.MapStructCycleTrackingContext context)
//	{
//		return new EmployeeEntity(employee, context);
//	}

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