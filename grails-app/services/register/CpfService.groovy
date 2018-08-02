package register

import grails.gorm.transactions.Transactional

@Transactional
class CpfService {
        Boolean validate(String cpf){
        try {
            cpf = cpf.replace("-", "")
            cpf = cpf.replace(".", "")
            int d1, d2
            int dg1, dg2, remainder
            int dgcpf

            d1 = d2 = 0
            dg1 = dg2 = remainder = 0

            for (int i = 1; i < cpf.length() -1; i++) {
                dgcpf = cpf.substring(i -1, i) as Integer
                d1 = d1 + ( 11 - i ) * dgcpf
                d2 = d2 + ( 12 - i ) * dgcpf
            }

            remainder = (d1 % 11)
            dg1 = (remainder < 2) ? 0 : 11 - remainder

            d2 += 2 * dg1
            remainder = (d2 % 11)
            dg2 = (remainder < 2) ? 0 : 11 - remainder

            String dgverif = cpf.substring(cpf.length()-2)
            String dgresult = (dg1 as String)  + (dg2 as String)

            return dgverif == dgresult
        } catch (Exception e) {
            return false
        }
    }
}
