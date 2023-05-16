package dagger.hilt.android.qualifiers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

@Qualifier
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
public @interface ApplicationContext {
}
