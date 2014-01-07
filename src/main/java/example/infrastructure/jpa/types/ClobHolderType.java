package example.infrastructure.jpa.types;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.type.TextType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.domain.example6.clob.ClobHolder;
import example.domain.example6.clob.ClobSerializer;

@Component
public class ClobHolderType extends AbstractCustomType implements CompositeUserType {

	private static ClobSerializer clobSerializer;

	@Autowired
	void setJsonSerializationService(ClobSerializer clobSerializer) {
		ClobHolderType.clobSerializer = clobSerializer;
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] { "type", "data" };
	}

	@Override
	public Type[] getPropertyTypes() {
		return new Type[] { StringType.INSTANCE, TextType.INSTANCE };
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class<ClobHolder> returnedClass() {
		return ClobHolder.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getPropertyValue(Object component, int propertyIndex) throws HibernateException {
		if (component == null) {
			return null;
		}

		ClobHolder clobHolder = (ClobHolder) component;
		if (!clobHolder.hasValue()) {
			return null;
		}

		switch (propertyIndex) {
		case 0:
			return clobHolder.getValue().getClass().getName();
		case 1:
			return clobSerializer.toClob(clobHolder.getValue());
		default:
			throw new HibernateException("Invalid property index [" + propertyIndex + "]");
		}
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {

		String type = StringType.INSTANCE.nullSafeGet(rs, names[0], session);
		String data = TextType.INSTANCE.nullSafeGet(rs, names[1], session);

		if (type == null && data == null) {
			return null;
		} else {
			Object json = clobSerializer.fromClob(data, type);
			return ClobHolder.of(json);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		ClobHolder clobHolder = (ClobHolder) value;

		if (clobHolder != null && clobHolder.getValue() != null) {
			StringType.INSTANCE.set(st, clobHolder.getValue().getClass().getName(), index, session);
			TextType.INSTANCE.set(st, clobSerializer.toClob(clobHolder.getValue()), index + 1, session);
		} else {
			StringType.INSTANCE.set(st, null, index, session);
			TextType.INSTANCE.set(st, null, index + 1, session);
		}
	}

}