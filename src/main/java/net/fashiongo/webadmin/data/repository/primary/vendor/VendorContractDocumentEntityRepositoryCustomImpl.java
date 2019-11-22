package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QVendorContractDocumentEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorContractEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorContractDocumentEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class VendorContractDocumentEntityRepositoryCustomImpl implements VendorContractDocumentEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public List<VendorContractDocumentEntity> findAllByVendorContractID(Integer vendorContractID) {

//		SELECT @WholeSalerID = WholeSalerID from [dbo].[Vendor_Contract] where VendorContractID = @VendorContractID;
//
//		SELECT
//		VCD.VendorContractDocumentID, VCD.VendorContractID, VCD.DocumentTypeID, VCD.FileName, VCD.FileName2, VCD.FileName3, VCD.Note, VCD.ReceivedBy, VCD.CreatedBy, VCD.CreatedOn, 0 as CheckBox
//		FROM [dbo].[Vendor_Contract] VC
//		INNER JOIN [dbo].[Vendor_Contract_Document] VCD
//				ON
//		VC.VendorContractID = VCD.VendorContractID
//		WHERE VC.WholeSalerID = @WholeSalerID
//				ORDER BY VendorContractDocumentID DESC

		JPAQuery<VendorContractDocumentEntity> jpaQuery = new JPAQuery<>(entityManager);
		QVendorContractEntity VC = new QVendorContractEntity("VC");
		QVendorContractEntity SUB_VC = new QVendorContractEntity("SUB_VC");
		QVendorContractDocumentEntity VCD = new QVendorContractDocumentEntity("VCD");


		jpaQuery.select(VCD)
				.from(VCD)
				.innerJoin(VCD.vendorContract,VC).fetchJoin()
				.where(
						VC.wholeSalerID.eq(
								JPAExpressions.select(SUB_VC.wholeSalerID)
								.from(SUB_VC)
								.where(SUB_VC.vendorContractID.eq(vendorContractID))
						)
				).orderBy(VCD.vendorContractDocumentID.desc());

		return jpaQuery.fetch();
	}

	@Override
	public VendorContractDocumentEntity findOneByVendorContractDocumentID(Integer vendorContractDocumentID) {
		QVendorContractDocumentEntity VCD = QVendorContractDocumentEntity.vendorContractDocumentEntity;
		JPAQuery<VendorContractDocumentEntity> query = new JPAQuery<>(entityManager);

		query.select(VCD)
				.from(VCD)
				.where(VCD.vendorContractDocumentID.eq(vendorContractDocumentID));

		return query.fetchFirst();
	}
}
