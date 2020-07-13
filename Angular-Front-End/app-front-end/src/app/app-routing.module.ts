import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommunitiesListComponent } from './components/communities-list/communities-list.component';


const routes: Routes = [
  {
    path: "communities",
    component: CommunitiesListComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
