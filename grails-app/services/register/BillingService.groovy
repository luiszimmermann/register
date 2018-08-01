package register

import grails.gorm.services.Service

@Service(Billing)
interface BillingService {

    Billing get(Serializable id)

    List<Billing> list(Map args)

    Long count()

    void delete(Serializable id)

    Billing save(Billing billing)

}