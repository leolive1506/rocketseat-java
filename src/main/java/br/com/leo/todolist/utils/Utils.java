package br.com.leo.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
  // (source da onde ta vindo, target pra onde vai
  public static void copyNonNullProperties(Object source, Object target) {
    // copiar valor de um objeto para outro, regra que vai utilizar
    BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
  }

  // pegar tudo que estiver como null
  public static String[] getNullPropertyNames(Object source) {
    /*
     * BeanWrapper -> interface fornece forma acessar propriedades de obj
     * BeanWrapperImpl -> implementação da interface
     */
    final BeanWrapper src = new BeanWrapperImpl(source);

    // gera array com as propriedades
    PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();

    for (PropertyDescriptor pd : pds) {
      // pegar value propriedade atual
      Object srcValue = src.getPropertyValue(pd.getName());

      // se estiver null, adiciona o NOME da propriedade no array de nomes vazios
      if (srcValue == null) {
        emptyNames.add(pd.getName());
      }
    }

    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }
}
