package net.fashiongo.webadmin.data.repository.primary.form;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.FashionGoFormEntity;
import net.fashiongo.webadmin.data.entity.primary.QFashionGoFormEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class FashionGoFormEntityRepositoryCustomImpl implements FashionGoFormEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<FashionGoFormEntity> getForms(FormOrderingType orderingType) {
		QFashionGoFormEntity qForm = QFashionGoFormEntity.fashionGoFormEntity;
		return new JPAQuery<>(entityManager)
				.select(qForm)
				.from(qForm)
				.orderBy(getOrderSpecifier(orderingType))
				.fetch();
	}

	private OrderSpecifier getOrderSpecifier(FormOrderingType orderingType) {
		QFashionGoFormEntity qForm = QFashionGoFormEntity.fashionGoFormEntity;
		switch (orderingType) {
			case FASHION_GO_FORM_ID_ASC:
				return qForm.fashionGoFormId.asc();
			case FASHION_GO_FORM_ID_DESC:
				return qForm.fashionGoFormId.desc();
			case FORM_NAME_ASC:
				return qForm.formName.asc();
			case FORM_NAME_DESC:
				return qForm.formName.desc();
			case ATTACHMENT_ASC:
				return qForm.attachment.asc();
			case ATTACHMENT_DESC:
				return qForm.attachment.desc();
			case MEMO_ASC:
				return qForm.memo.asc();
			case MEMO_DESC:
				return qForm.memo.desc();
			case MODIFIED_ON_ASC:
				return qForm.modifiedOn.asc();
			case MODIFIED_ON_DESC:
				return qForm.modifiedOn.desc();
			default:
				return qForm.fashionGoFormId.desc();
		}
	}
}
