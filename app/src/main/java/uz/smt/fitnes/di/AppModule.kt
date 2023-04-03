package uz.smt.fitnes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.smt.common_utils.navigator.Navigator
import uz.smt.fitnes.navigator.NavigatorProvider
import javax.inject.Singleton

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 3/13/2023 4:29 PM for Fitnes.
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideNavigateProvider(): Navigator.Provider {
        return NavigatorProvider()
    }
}